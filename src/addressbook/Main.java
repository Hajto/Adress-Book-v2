package addressbook;

import addressbook.Controllers.editViewController;
import addressbook.Controllers.mainController;
import addressbook.Controllers.personOverviewController;
import addressbook.Model.Person;
import addressbook.Utility.Refference.fxmlRefference;
import addressbook.Utility.XML.XMLPersonBuilder;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private BorderPane root;
    private Stage primarStage;

    private ObservableList<Person> persons;

    private personOverviewController personController;

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primarStage = primaryStage;
        primaryStage.setTitle("Fancy Address Book");
        primaryStage.show();
        startRoot();
        loadPersonOverview();
        primaryStage.setResizable(false);

    }
    public static void main(String[] args) {
        launch(args);
    }

    public void startRoot(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlRefference.mainFXML));
            root = loader.load();
            Scene scene = new Scene(root,800,500);
            primarStage.setScene(scene);

            mainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean editDialog(Person person, boolean add) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlRefference.editViewFXML));
            AnchorPane root = loader.load();

            //Creating entirely new stage
            Stage stage = new Stage();
            stage.setResizable(false);
            if (add) {
                stage.setTitle("Add Person");
            } else {
                stage.setTitle("Edit Person");
            }
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primarStage);
            Scene scene = new Scene(root);
            stage.setScene(scene);

            editViewController controller = loader.getController();
            controller.setPerson(person);
            controller.setStage(stage);

            stage.showAndWait();

            return controller.isOk();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void loadPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlRefference.personOverviewFXML));
            AnchorPane overview = loader.load();
            root.setCenter(overview);
            personController =  loader.getController();
            personController.setMainApp(this);
            persons = personController.getPersons();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimarStage(){
        return primarStage;
    }

    public void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLPersonBuilder.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            XMLPersonBuilder wrapper = new XMLPersonBuilder();
            wrapper.setPersons(persons);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readDataFromFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(XMLPersonBuilder.class);
            Unmarshaller un = context.createUnmarshaller();
            XMLPersonBuilder personBuilder = (XMLPersonBuilder) un.unmarshal(file);

            List<Person> debug = personBuilder.getPersons();
            System.out.println(debug.get(0).getId());
            personController.setPersons(personBuilder.getPersons());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

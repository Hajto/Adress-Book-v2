package addressbook;

import addressbook.Controllers.editViewController;
import addressbook.Model.Person;
import addressbook.Utility.refference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private BorderPane root;
    private Stage primarStage;

    public Main(){}

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource(refference.mainFXML));
        primarStage = primaryStage;
        primaryStage.setTitle("Fancy Address Book");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        loadPersonOverview();
        primaryStage.setResizable(false);
    }

    public boolean editDialog(Person person){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(refference.editViewFXML));
            AnchorPane root = loader.load();

            //Creating entirely new stage
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Edit Person Info");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primarStage);
            Scene scene = new Scene(root);
            stage.setScene(scene);

            editViewController controller = loader.getController();
            controller.setPerson(person);
            controller.setStage(stage);

            stage.showAndWait();

            return controller.isOk();
        } catch (IOException e){
            e.printStackTrace();
            return false;
       }

    }

    private void loadPersonOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(refference.personOverviewFXML));
            AnchorPane overview = loader.load();
            root.setCenter(overview);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

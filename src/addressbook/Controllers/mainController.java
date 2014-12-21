package addressbook.Controllers;

import addressbook.Main;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class mainController {

    Main mainApp;
    private Stage stage;

    @FXML
    public void debug() {
        System.out.println("Debug");
    }

    @FXML
    public void onSave() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Choose file by file chooser
        File file = fileChooser.showSaveDialog(mainApp.getPrimarStage());
        System.out.println(file);

        mainApp.saveDataToFile(file);
    }
    @FXML
    public void onRead(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimarStage());

        mainApp.readDataFromFile(file);
    }

    public void setMainApp(Main app){
        this.mainApp = app;
    }

}

package addressbook.Controllers;

import addressbook.Main;
import addressbook.Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by Hajto-Lenovo on 2014-12-19.
 */
public class personOverviewController {

    public personOverviewController() {
    }

    @FXML
    private TableView personsOverview;
    @FXML
    private TableColumn<Person, String> colName;
    @FXML
    private TableColumn<Person, String> surCol;

    @FXML
    private Label lName;
    @FXML
    private Label lLast;
    @FXML
    private Label lAddress;

    private ObservableList<Person> persons = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        addDebugData();
        colName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        surCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    @FXML
    private void tableClick() {
        Person holder = (Person) personsOverview.getSelectionModel().getSelectedItem();
        try {
            lName.setText(holder.getFirstName());
            lLast.setText(holder.getLastName());
            lAddress.setText(holder.getAddress());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void editClick(){
        
    }

    @FXML
    private void deleteClick() {
        int temp = personsOverview.getSelectionModel().getSelectedIndex();
        if (temp >= 0)
            personsOverview.getItems().remove(temp);
    }

    private void addDebugData() {
        persons.add(new Person("Kasia", "Debugerka"));
        persons.add(new Person("InnaKasia", "Debugerka"));
        persons.add(new Person("Kasia", "Debugerka"));
        persons.add(new Person("Kasia", "Debugerka"));
        persons.add(new Person("Kasia", "Debugerka"));
    }

    @FXML
    private void debug() {
        personsOverview.setItems(persons);
    }
}


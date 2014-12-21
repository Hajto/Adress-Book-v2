package addressbook.Controllers;

import addressbook.Main;
import addressbook.Model.Person;
import addressbook.SQL.SQLite;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class personOverviewController {

    public personOverviewController() {
        //Co to za klasa bez konstruktora :D
    }

    Main mainApp = new Main();
    @FXML
    private TableView<Person> personsOverview;
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
    @FXML
    private Label lPhone;
    @FXML
    private Label lBirth;

    private ObservableList<Person> persons;
    SQLite sql;


    @FXML
    private void initialize() {
        colName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        surCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        sql = new SQLite();
        persons = sql.selectAll();
        personsOverview.setItems(persons);
    }
    @FXML
    private void tableClick() {
        Person holder = personsOverview.getSelectionModel().getSelectedItem();
        showDetails(holder);
        //TODO: Wywalic ponizsze, bo tylko debug
        System.out.println(holder.getId());
    }
    @FXML
    private void editClick() {
        Person selected = personsOverview.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean result = mainApp.editDialog(selected,false);
            if(result){
                showDetails(selected);

                String temp = "firstName='"+selected.getFirstName()+"', lastName='"+selected.getLastName() + "', address='"+selected.getAddress() + "', phone='"+selected.getPhone() + "', birthday='"+selected.getBirthday()+"'";
                System.out.println(temp);
                sql.sendEdit(temp,Integer.toString(selected.getId()));
            }
        }
    }
    @FXML
    private void addClick(){
        Person newPerson = new Person(sql.getNextId());
        boolean result = mainApp.editDialog(newPerson,true);
        if(result){
            persons.add(newPerson);
            sql.addToPersons(newPerson);
        }
    }
    @FXML
    private void deleteClick() {
        int temp = personsOverview.getSelectionModel().getSelectedIndex();
        if (temp >= 0) {
            Person tp = personsOverview.getSelectionModel().getSelectedItem();
            personsOverview.getItems().remove(temp);
            sql.deletePerson(Integer.toString(tp.getId()));
            showDetails(null);
        }
    }

    public void showDetails(Person person) {
        if(person != null) {
            lName.setText(person.getFirstName());
            lLast.setText(person.getLastName());
            lAddress.setText(person.getAddress());
            lBirth.setText(person.getBirthday());
            lPhone.setText(person.getPhone());
        } else {
            lName.setText("");
            lLast.setText("");
            lAddress.setText("");
            lBirth.setText("");
            lPhone.setText("");
        }

    }

}


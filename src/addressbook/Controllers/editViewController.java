package addressbook.Controllers;

import addressbook.Model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editViewController {

    @FXML
    private TextField txFname;
    @FXML
    private TextField txLname;
    @FXML
    private TextField txAddress;
    @FXML
    private TextField txPhone;
    @FXML
    private TextField txBirth;

    private Person person;
    private Stage stage;
    private boolean okClicked = false;

    public editViewController(){

    }

    public void setPerson(Person person) {
        this.person = person;

        txFname.setText(person.getFirstName());
        txLname.setText(person.getLastName());
        txAddress.setText(person.getAddress());
        txBirth.setText(person.getAddress());
        txPhone.setText(person.getPhone());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onOk(){
        person.setFirstName(txFname.getText());
        person.setLastName(txLname.getText());
        person.setAddress(txAddress.getText());
        person.setBirthday(txBirth.getText());
        person.setPhone(txPhone.getText());

        okClicked = true;
        stage.close();
    }

    @FXML
    private void onCancel(){
        stage.close();
    }

    public boolean isOk(){
        return okClicked;
    }
}

package addressbook.Model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hajto-Lenovo on 2014-12-19.
 */
public class Person {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty birthday;
    private SimpleStringProperty address;
    private SimpleStringProperty phone;
    private Integer Id;

    public Person(String name,String surnName){
        this.firstName = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(surnName);
        this.birthday = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
    }
    public Person(String name, String surnName, String birthday, String address, String phone, Integer Id){
        this.firstName = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(surnName);
        this.birthday = new SimpleStringProperty(birthday);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.Id = Id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}

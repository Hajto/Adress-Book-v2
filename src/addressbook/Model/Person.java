package addressbook.Model;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty birthday;
    private SimpleStringProperty address;
    private SimpleStringProperty phone;
    private Integer Id;
    private static int previousId=0;

    public Person(){
        this(null,null,null,null,null,previousId+1);
        previousId++;
    }

    //For creating new user from form
    public Person(int id){
        this("","","","","",id+1);
    }
    //Creating new person from Database for viewing
    public Person(String name, String surnName, String address, String phone, String birthday, Integer Id){
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

    public int getId(){
        return this.Id;
    }
}

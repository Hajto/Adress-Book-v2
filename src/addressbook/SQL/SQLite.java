package addressbook.SQL;

import addressbook.Model.Person;
import addressbook.Utility.Refference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SQLite {

    private Connection conn;
    private Statement stat;

    public SQLite(){
        try{
            Class.forName(Refference.sqliteDriver);
            System.out.println("Connection ready");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(Refference.dbUrl);
            stat = conn.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
        tryCreateTables();
    }

    private void tryCreateTables(){
        String createQuery = "CREATE TABLE IF NOT EXISTS persons (Id INTEGER PRIMARY KEY AUTOINCREMENT, firstName VARCHAR(64), lastName VARCHAR(64), address VARCHAR(64), phone VARCHAR(24), birthday VARCHAR(24))";
        try {
            stat.execute(createQuery);
            System.out.println("Table ready");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Person> selectAll(){
        String selectQuery = "select * from persons";
        try {
            ResultSet rset = stat.executeQuery(selectQuery);
            ObservableList<Person> persons = FXCollections.observableArrayList();
            while (rset.next()){
                persons.add(new Person(rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),rset.getInt(1)));
            }
            return persons;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addToPersons(Person person){
        try {
            PreparedStatement insertQuery = conn.prepareStatement("INSERT INTO persons (firstName,lastName,address,phone,birthday) VALUES(?,?,?,?,?)");
            insertQuery.setString(1,person.getFirstName());
            insertQuery.setString(2,person.getLastName());
            insertQuery.setString(3,person.getAddress());
            insertQuery.setString(4,person.getPhone());
            insertQuery.setString(5,person.getBirthday());
            insertQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getNextId(){
        String query = "SELECT t.* FROM sqlite_sequence t";
        try {
            ResultSet rs = stat.executeQuery(query);
            String debug = rs.getString("seq");
            return Integer.parseInt(debug);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deletePerson(String Id){
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM persons WHERE Id=?");
            query.setString(1,Id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendEdit(String updated, String Id){
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE persons SET "+updated + "where Id="+Id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

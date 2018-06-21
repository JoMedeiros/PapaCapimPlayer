package br.imd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty password;

    public User(){
        this(0, "");
    }

    public User(int id, String pw){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty("");
        this.password = new SimpleStringProperty(pw);
    }

    // Setters and getters

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName(){
        return name.get();
    }

    public void setPassword(String pw){
        this.password.set(pw);
    }

    public String getPassword(){
        return password.get();
    }
}

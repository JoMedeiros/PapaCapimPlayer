package br.imd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty id;
    private final StringProperty password;
    private final StringProperty name;

    public User(){
        this(0, "");
    }

    public User(int id, String pw){
        this.id = new SimpleIntegerProperty(id);
        this.password = new SimpleStringProperty(pw);
        this.name = new SimpleStringProperty("");
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

    public void setPassword(String pw){
        this.password.set(pw);
    }

    public String getPassword(){
        return password.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName(){
        return name.get();
    }
}

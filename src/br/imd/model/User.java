package br.imd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {
    private final IntegerProperty id;



    public User(int id){
        this.id = new SimpleIntegerProperty(id);
    }

    // Setters and getters

    public int getId() {
        return id.get();
    }
}

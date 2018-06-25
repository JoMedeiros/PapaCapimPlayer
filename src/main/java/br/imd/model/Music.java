package br.imd.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class Music {
    private final StringProperty title;
    private final StringProperty path;

    //private String trackPath;
    private Media media;

    /**
     * The Constructor
     * @param path The path to the file
     */
    public Music(String path){
        File f = new File(path);
        this.title = new SimpleStringProperty(f.getName());
        this.path = new SimpleStringProperty(path);
    }

    // Getters e setters
    public String getTitle(){
        return title.get();
    }

    public void setTitle(String title){
        this.title.set(title);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty titleProperty(){
        return title;
    }

    public Media getMedia(){
        try {
            this.media = new Media(path.get());
            if (media.getError() == null){
                media.setOnError(new Runnable() {
                    @Override
                    public void run() {
                        // Handle asynchronous error in Media object.
                    }
                });
                media.getMetadata().addListener(new MapChangeListener<String, Object>() {
                    @Override
                    public void onChanged(Change<? extends String, ? extends Object> ch) {
                        if (ch.wasAdded()) {
                            // Get metadata funtion
                        }
                    }
                });
                //this.title.set((String) media.getMetadata().get("título"));
            }
        } catch (Exception e){
            System.out.println("Exception trying to create instatiate music from: " + path);
            System.out.printf(e.getMessage());
        }
        return this.media;
    }

}

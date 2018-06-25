package br.imd.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
        this.title = new SimpleStringProperty();
        this.path = new SimpleStringProperty(path);

        //this.trackPath = path;
        try {
            this.media = new Media(path);
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
                            handleMetadata(ch.getKey(), ch.getValueAdded());
                        }
                    }
                });

                System.out.println("Título: " + title.get());
                //this.title.set((String) media.getMetadata().get("título"));
            }
        } catch (Exception e){
            System.out.println("Exception trying to create instatiate music from: " + path);
            System.out.printf(e.getMessage());
        }
    }

    private void handleMetadata(String key, Object value) {
        if (key.equals("title")) {
            this.title.set((String) value.toString());
        }
        /* other keys: ("album"), ("artist"), ("year"), ("image") */
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
            this.media = new Media(this.path.get());
            if (media.getError() == null){
                media.setOnError(new Runnable() {
                    @Override
                    public void run() {
                        // Handle asynchronous error in Media object.
                    }
                });
            }
        } catch (Exception e){
            System.out.println("Não foi possível criar media");
        }
        return this.media;
    }

}

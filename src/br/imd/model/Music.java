package br.imd.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Music {
    private final StringProperty title;
    private final StringProperty path;

    private String trackPath;
    //= "samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3";
    private Media media;

    /**
     * The Constructor
     * @param path The path to the file
     */
    public Music(String path){
        this.title = new SimpleStringProperty("Untitled");
        this.path = new SimpleStringProperty(path);

        this.trackPath = path;
        try {
            this.media = new Media(this.trackPath);
            if (media.getError() == null){
                media.setOnError(new Runnable() {
                    @Override
                    public void run() {
                        // Handle asynchronous error in Media object.
                    }
                });
            }
        } catch (Exception e){
            //System.out.println("Não foi possível criar media");
            System.out.printf(e.getMessage());
        }
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

    public Media getMedia(){
        try {
            this.media = new Media(this.trackPath);
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

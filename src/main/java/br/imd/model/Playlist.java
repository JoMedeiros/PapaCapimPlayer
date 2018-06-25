package br.imd.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.*;

// Imports of JSON
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Playlist {
    private final StringProperty title;
    private final IntegerProperty user; //< The user id
    private int currentIndex; //< index of the current song

    private boolean shuffleOn = false;
    //private Map<String, String> playlist;
    private ObservableList<Music> songs = FXCollections.observableArrayList();

    /**
     * Default Constructor
     */
    public Playlist(){this("Untitled", new User());}

    /**
     * Constructor
     * @param title
     * @param user
     */
    public Playlist(String title, User user)
    {
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleIntegerProperty(user.getId());
        // Adding one sample song to the playlist
        //String URI = new File("samples/Die+Walk%C3%BCre,+WWV+86B+-+Ride+of+the+Valkyries.mp3").toURI().toString();
        //this.songs.add(new Music(URI));
        this.currentIndex = -1; //
    }

    /**
     * Create a new Music object on the playlist
     * @param filePath
     */
    public void addSong(String filePath){
        try {
            String URI = new File(filePath).toURI().toString();
            this.songs.add(new Music(URI));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Create a new Music object on the playlist
     * @param music
     */
    public void addSong(Music music){
        this.songs.add(music);
    }

    /**
     * Gets the next song to be played, the next in sequence if `shuffle` is false, a random song otherwise
     * @return
     */
    public Music getNext(){
        if (songs.isEmpty()){
            return null;
        }
        //Music m = new Music("file:/home/jimmy/Documentos/github_workspace/" +
        //        "PapaCapimPlayer/samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3");
        //currentIndex += 1;
        //currentIndex %= songs.size();
        return songs.get(currentIndex);
    }

    /**
     * Returns the data as an observable list of Music.
     * @return
     */
    public ObservableList<Music> getSongsData(){
        return songs;
    }

    /**
     * Returns the StringProperty that holds the Title.
     * @return
     */
    public StringProperty titleProperty(){
        return title;
    }
}

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
import java.io.FileWriter;
import java.io.IOException;
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
    public Playlist() throws IOException { this("Untitled", new User()); }

    /**
     * Constructor
     * @param title
     * @param user
     */
    @SuppressWarnings("unchecked")
    public Playlist(String title, User user) throws IOException
    {
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleIntegerProperty(user.getId());

        JSONObject pl = new JSONObject();
        pl.put("Title", title);
        pl.put("User", user.getId());

        JSONArray songs = new JSONArray();
        pl.put("Songs", songs);

        this.currentIndex = -1;

        try (FileWriter file = new FileWriter("./src/main/java/assets/playlists/"+ title +".json")) {
            file.write(pl.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        }
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

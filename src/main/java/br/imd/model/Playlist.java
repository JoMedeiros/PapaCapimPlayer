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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Imports of JSON
import org.json.simple.parser.JSONParser;
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
    public Playlist(String title, User user)
    {
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleIntegerProperty(user.getId());

        JSONObject pl = new JSONObject();
        pl.put("Title", title);
        pl.put("User", user.getId());

        JSONArray songs = new JSONArray();
        pl.put("Songs", songs);

        try {
            generateJsonFile(title, pl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.currentIndex = -1;
    }

    private void generateJsonFile(String title, JSONObject obj) throws IOException
    {
        try (FileWriter file = new FileWriter("./src/main/java/assets/playlists/"+ title +".json")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        }
    }

    /**
     * Create a new Music object on the playlist
     * @param filePath
     */
    @SuppressWarnings("unchecked")
    public void addSong(String filePath){
        JSONParser parser = new JSONParser();
        try
        {
            // Creates an object with the playlist
            Object income = parser.parse(new FileReader("./src/main/java/assets/playlists/" + this.title.getValue() + ".json"));
            JSONObject playlist = (JSONObject) income;

            // Retrieves all of its songs
            JSONArray songsList = (JSONArray) playlist.get("Songs");

            String URI = new File(filePath).toURI().toString();

            songsList.add(URI);
            playlist.put("Songs", songsList);

            this.songs.add(new Music(URI));

            generateJsonFile(title.getValue(), playlist);
        }
        catch (Exception e)
        {
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

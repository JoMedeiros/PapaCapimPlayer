package br.imd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Map;

public class Playlist {
    private final StringProperty title;
    private final IntegerProperty user; //< The user id
    private int currentIndex; //< index of the current song

    private boolean shuffleOn = false;
    //private Map<String, String> playlist;
    private ArrayList<Music> songs;

    /**
     * Default Constructor
     */
    public Playlist(){this("Untitled", new User(-1));}

    /**
     * Constructor
     * @param title
     * @param user
     */
    public Playlist(String title, User user){
        this.title = new SimpleStringProperty(title);
        this.user = new SimpleIntegerProperty(user.getId());
        // Initializing the playlist with one song
        this.songs = new ArrayList();
        this.songs.add(new Music("file:/home/jimmy/Documentos/github_workspace/" +
                "PapaCapimPlayer/samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3"));
        this.currentIndex = 0;
    }

    /**
     * Gets the next song to be played, the next in sequence if `shuffle` is false, a random song otherwise
     * @return
     */
    public Music getNext(){
        //Music m = new Music("file:/home/jimmy/Documentos/github_workspace/" +
        //        "PapaCapimPlayer/samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3");
        return songs.get(currentIndex);
    }

    /**
     * Returns the StringProperty that holds the Title.
     * @return
     */
    public StringProperty titleProperty(){
        return title;
    }
}

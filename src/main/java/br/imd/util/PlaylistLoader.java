package br.imd.util;

import br.imd.model.Playlist;
import br.imd.model.User;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

public class PlaylistLoader
{
    @SuppressWarnings("unchecked")
    public ObservableList<Playlist> loadPlaylist (User user, ObservableList<Playlist> playlists)
    {
        JSONParser parser = new JSONParser();
        File files = new File("./src/main/java/assets/playlists/" + user.getId() + "/");

        // There ain't a playlist created by this user
        if (! files.isDirectory()) { return playlists; }

        // If it got here, this user created at least one playlist. Lets iterate the files.
        for (File e : files.listFiles() )
        {
            try
            {
                Object income = parser.parse(new FileReader(e.getPath()));
                JSONObject playlist = (JSONObject) income;
                String playlistTitle = (String) playlist.get("Title");

                // Create the playlist object
                Playlist newPlaylist = new Playlist(playlistTitle, user, false);

                // Adds all the songs
                JSONArray songs = (JSONArray) playlist.get("Songs");
                Iterator<String> iterator = songs.iterator();

                // Adds all the songs to the new Playlist object
                while(iterator.hasNext()) { newPlaylist.addSong(iterator.next(), false); }

                // Adds the new playlist to the Observable Array List of Playlists
                playlists.add(newPlaylist);
            }
            catch (ParseException e1)
            {
                e1.printStackTrace();
            }
            catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }

        return playlists;
    }
}
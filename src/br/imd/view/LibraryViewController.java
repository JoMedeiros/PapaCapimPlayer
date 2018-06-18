package br.imd.view;

import br.imd.model.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LibraryViewController {

    String title = "Untitled";

    @FXML
    public Label playlistTitle;

    public LibraryViewController(){
        playlistTitle = new Label(title);
    }

    private void showPlaylist(Playlist playlist){
        if (playlist != null){
            // Fill the Label of title and @TODO show the songs on the table
            playlistTitle.setText(playlist.titleProperty().get());
        } else {
            // Playlist not selected, remove everything
            playlistTitle.setText("");
        }
    }

    @FXML
    private void initialize(){
        showPlaylist(null);
    }

}

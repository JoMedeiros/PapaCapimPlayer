package br.imd.view;

import br.imd.Main;
import br.imd.model.Music;
import br.imd.model.Playlist;
import br.imd.model.User;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class UserOverviewController {
    @FXML
    private TableView<Music> songsTable;
    @FXML
    private TableColumn<Music, String> songsColumn;
    @FXML
    private TableView<Playlist> playlistTable;
    @FXML
    private TableColumn<Playlist, String> playlistsColumn;
    @FXML
    private TableView<Music> playlistSongsTable;
    @FXML
    private TableColumn<Music, String> playlistSongsColumn;
    @FXML
    public Label playlistLabel;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserOverviewController(){ }

    @FXML
    private void initialize(){
        // Initialize the playlists table with the column.
        playlistsColumn.setCellValueFactory(cellData ->
                cellData.getValue().titleProperty());

        // Initialize the songs overview.
        showPlaylist(null);

        // Listen for selection changes and show the playlist details.
        playlistTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPlaylist(newValue))
        );

        // Initialize the songs table with the column.
        songsColumn.setCellValueFactory(cellData ->
                cellData.getValue().titleProperty());
        // Listen for double click on a music to play it
        songsTable.setRowFactory( tv -> {
            TableRow<Music> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Music music = row.getItem();
                    main.sendToPlayer(music); // Sends music to Main
                }
            });
            return row ;
        });
        // Listen for double click on a music to play it
        playlistSongsTable.setRowFactory( tv -> {
            TableRow<Music> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Music music = row.getItem();
                    main.sendToPlayer(music);// Sends music to Main
                }
            });
            return row ;
        });
    }

    /**
     * Shows the playlist in the GUI
     * @param playlist
     */
    private void showPlaylist(Playlist playlist){
        if (playlist != null){
            // Fill the Label of title
            playlistLabel.setText(playlist.titleProperty().get());
            // Initialize the songs table with the column.
            playlistSongsTable.setItems(playlist.getSongsData());
            playlistSongsColumn.setCellValueFactory(cellData ->
                    cellData.getValue().titleProperty());

        } else {
            // Playlist not selected, remove everything
            playlistLabel.setText("No playlist selected");
        }
    }
    /**
     * Shows the playlist in the GUI
     * @param playlist
     */
    private void showSongs(Playlist playlist){
        if (playlist != null){
            // Initialize the songs table with the column.
            songsTable.setItems(playlist.getSongsData());
            songsColumn.setCellValueFactory(cellData ->
                    cellData.getValue().titleProperty());

        } else {
            // Playlist not selected, remove everything
            playlistLabel.setText("No playlist selected");
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param main
     */
    public void setMain(Main main){
        this.main = main;

        // @TODO Add observable list data to the table
        playlistTable.setItems(main.getPlaylistData());
        songsTable.setItems(main.getMusicData());
    }

    /**
     * Logout function.
     */
    public void handleLogout(){
        main.start(main.getPrimaryStage());
    }

}
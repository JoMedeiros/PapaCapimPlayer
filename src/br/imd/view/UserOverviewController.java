package br.imd.view;

import br.imd.Main;
import br.imd.model.Playlist;
import br.imd.model.User;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserOverviewController {
    @FXML
    private TableView<Playlist> playlistTable;
    @FXML
    private TableColumn<Playlist, String> playlistsColumn;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserOverviewController(){
    }

    @FXML
    private void initialize(){
        // Initialize the person table with the two columns.
        playlistsColumn.setCellValueFactory(cellData ->
            cellData.getValue().titleProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param main
     */
    public void setMain(Main main){
        this.main = main;

        // @TODO Add observable list data to the table
        playlistTable.setItems(main.getPlaylistData());
    }


}

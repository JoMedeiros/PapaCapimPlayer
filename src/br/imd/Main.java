package br.imd;

import br.imd.model.Playlist;
import br.imd.model.User;
import br.imd.view.UserOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * The data as an observable list of Playlists
     */
    private ObservableList<Playlist> playlistData = FXCollections.observableArrayList();
    /**
     * Logged in User
     */
    private User currentUser;

    /**
     * Constructor
     */
    public Main(){
        // @TODO change the user
        this.currentUser = new User(42);
        playlistData.add(new Playlist("Classic",this.currentUser));
        playlistData.add(new Playlist("Raça Negra", this.currentUser));
        playlistData.add(new Playlist("Roquizin", this.currentUser));
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Playlist> getPlaylistData(){
        return playlistData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PapaCapimPlayer");

        initRootLayout();
        // @TODO place a if statement to verify login
        showUser();
        showPlayer();
    }

    /**
     * Initilizes the root layout.
     */
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(this.rootLayout);
            primaryStage.setScene(scene);

            // Setting Stage dimensions
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the User Overview.
     */
    public void showUser(){
        try {
            // Load the User layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/UserOverview.fxml"));
            AnchorPane userOverview = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            UserOverviewController controller = loader.getController();
            controller.setMain(this);

            rootLayout.setRight(userOverview);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Initializes the MediaPlayer.
     */
    public void showPlayer() {
        try {
            // Load the User layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MediaPlayer.fxml"));
            AnchorPane playerOverview = (AnchorPane) loader.load();

            rootLayout.setBottom(playerOverview);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

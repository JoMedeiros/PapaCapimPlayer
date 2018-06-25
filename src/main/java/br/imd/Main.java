package br.imd;

import br.imd.model.Music;
import br.imd.model.Playlist;
import br.imd.model.User;
import br.imd.model.UserListWrapper;
import br.imd.util.PlaylistLoader;
import br.imd.view.LoginPageController;
import br.imd.view.MediaPlayerController;
import br.imd.view.RootLayoutController;
import br.imd.view.UserOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private MediaPlayerController playerController;

    /**
     * The data as an observable list of Playlists
     */
    private ObservableList<Playlist> playlistData = FXCollections.observableArrayList();

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<User> userData = FXCollections.observableArrayList();

    /**
     * The data as an observable list of Musics
     */
    private ObservableList<Music> musicData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main(){
        // @TODO change the user
        User currentUser = new User(42, "");
        PlaylistLoader pLoader = new PlaylistLoader();
        playlistData = pLoader.loadPlaylist(currentUser, playlistData);
    }

    /**
     * Returns the data as an observable list of Playlists.
     * @return
     */
    public ObservableList<Playlist> getPlaylistData(){
        return playlistData;
    }

    /**
     * Returns the data as an observable list of Users.
     * @return
     */
    public ObservableList<User> getUserData(){
        return userData;
    }

    /**
     * Returns the data as an observable list of Musics.
     * @return
     */
    public ObservableList<Music> getMusicData(){
        return musicData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PapaCapimPlayer");
        // @TODO place a if statement to verify user login
        File file = new File("usersConfig.xml");
        if (file != null) {
            loadUserDataFromFile(file);
        } else {
            System.out.println("usersConfig.xml not found");
        }
        System.out.println(userData.toString());
        showLoginPage();
        //initRootLayout();
        //showUser();
        //showPlayer();
    }

    /**
     * Initilizes the root layout.
     */
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            Scene scene = new Scene(rootLayout);
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
     * Initializes the Login Page
     */
    public void showLoginPage(){
        try {
            // Load the User layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/LoginPage.fxml"));
            AnchorPane loginPage = loader.load();

            Scene scene = new Scene(loginPage);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            LoginPageController controller = loader.getController();
            controller.setMain(this);

            /// Setting Stage dimensions
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            primaryStage.show();
        } catch (IOException e){
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
            AnchorPane userOverview = loader.load();

            // Give the controller access to the main app.
            UserOverviewController controller = loader.getController();
            controller.setMain(this);

            rootLayout.setCenter(userOverview);
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
            AnchorPane playerOverview = loader.load();
            playerController = loader.getController(); // Sets a reference to the player

            rootLayout.setBottom(playerOverview);
        } catch (IOException e){
            System.out.println("Exception loading Media Player fxml file");
            e.printStackTrace();
        }
    }

    /**
     * Recieves a Music from UserOverviewController and sends to MediaPlayerController
     */
    public void sendToPlayer(Music music){
        playerController.playSong(music);
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

    public File getUserFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setUserFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");

        }
    }

    /**
     * Loads user data from the specified file. The current user data will
     * be replaced.
     *
     * @param file
     */
    public void loadUserDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(UserListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            UserListWrapper wrapper = (UserListWrapper) um.unmarshal(file);

            userData.clear();
            userData.addAll(wrapper.getUsers());

            // Save the file path to the registry.
            setUserFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}
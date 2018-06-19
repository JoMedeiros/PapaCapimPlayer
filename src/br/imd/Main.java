package br.imd;

import br.imd.model.Playlist;
import br.imd.model.User;
import br.imd.model.UserListWrapper;
import br.imd.view.LoginPageController;
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

    /**
     * The data as an observable list of Playlists
     */
    private ObservableList<Playlist> playlistData = FXCollections.observableArrayList();

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<User> userData = FXCollections.observableArrayList();

    /**
     * Logged in User
     */
    private User currentUser;

    /**
     * Constructor
     */
    public Main(){
        //loadUserDataFromFile(new File("usersConfig.xml"));
        // @TODO change the user
        this.currentUser = new User(42);
        playlistData.add(new Playlist("Classic",this.currentUser));
        playlistData.get(0).addSong("samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3");
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
        // @TODO place a if statement to verify user login
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
     * Initializes the Login Page
     */
    public void showLoginPage(){
        try {
            // Load the User layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/LoginPage.fxml"));
            AnchorPane loginPage = (AnchorPane) loader.load();

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
            AnchorPane userOverview = (AnchorPane) loader.load();

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
            AnchorPane playerOverview = (AnchorPane) loader.load();

            rootLayout.setBottom(playerOverview);
        } catch (IOException e){
            System.out.println("Exception loading Media Player fxml file");
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
//            UserListWrapper wrapper = (UserListWrapper) um.unmarshal(file);

            userData.clear();
//            userData.addAll(wrapper.getUsers());

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

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
}
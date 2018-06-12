package br.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

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
            AnchorPane userOverview = (AnchorPane) loader.load();

            rootLayout.setBottom(userOverview);
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

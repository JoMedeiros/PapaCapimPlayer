import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Player extends Application {

    String trackPath1 = "samples/Tchaikovsky+-+The+Nutcracker+Suite+-+Act+I,+No.8.+Waltz+of+the+Flowers.mp3";
    String trackPath = "samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3";
    Media pick = new Media(new File(trackPath).toURI().toString());
    //Media pick = new Media("file://" + trackPath);
    MediaPlayer player = new MediaPlayer(pick);

    @Override
    public void start(Stage primaryStage) {

        // Add a mediaView, to display the media. Its necessary !
        // This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);

        // Add to scene
        Group root = new Group(mediaView);
        Scene scene = new Scene(root, 500, 200);

        // Show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void play(){
        // Play the media once the stage is shown
        player.play();
    }
    public void pause(){
        player.pause();
    }
    public void increaseVol(){
        player.setVolume(player.getVolume() + 0.1);
    }
    public void decreaseVol(){
        player.setVolume(player.getVolume() - 0.1);
    }
}

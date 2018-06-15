package br.imd.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;

public class MediaPlayerController {

    @FXML
    private Button playPauseBtn;

    // Player atributes
    private boolean isPlaying = false;
    private String trackPath = "samples/Mozart_Eine_kleine_Nachtmusik_KV525_Satz_4_Rondo.mp3";
    private Media song;
    private MediaPlayer player;
    private MediaView mediaView;

    public MediaPlayerController(){
        this.song = new Media(new File(trackPath).toURI().toString());
        this.player = new MediaPlayer(song);
        this.mediaView = new MediaView(player);
    }

    /**
     * Plays the loaded music in the player or pauses if it is playing.
     */
    public void play(){
        // @TODO
        String URI;
        if (isPlaying) {
            URI = new File("src/br/imd/imgs/icons8-reproduzir-50.png").toURI().toString();
            player.pause();
            isPlaying = false;
        } else {
            URI = new File("src/br/imd/imgs/icons8-pausa-50.png").toURI().toString();
            player.play();
            isPlaying = true;
        }
        // Opening the image
        try {
            ImageView view = new ImageView(new Image(URI));
            playPauseBtn.setGraphic(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the player reseting the song.
     */
    public void stop(){
        player.stop();
    }


    public void playSong() {
        //String songPath = playlist.getNextPath()
        String songPath = "samples/Die+Walk%C3%BCre,+WWV+86B+-+Ride+of+the+Valkyries.mp3";
        Media song = new Media(new File(songPath).toURI().toString());
        player = new MediaPlayer(song);
        player.play();

        //player.setOnPaused(song);
    }

}

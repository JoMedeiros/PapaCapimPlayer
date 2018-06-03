import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    boolean isPlaying = false;
    Player player = new Player();
    Stage window;
    Scene scene1, scene2;

    Button playPauseBtn;
    Button StopBtn;
    Button backwardBtn;
    Button forwardBtn;
    Button previousBtn;
    Button nextBtn;
    Button shuffleBtn;
    Button repeatBtn;
    Button repeatOnceBtn;
    Button infoBtn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        Label label1 = new Label("Welcome to PapaCapim");
        Button button1 = new Button("Go to library");
        button1.setOnAction(e -> window.setScene(scene2));

        //Buttons Initialization
        playPauseBtn = new Button("\u23F5");
        StopBtn = new Button("\u23F9");
        backwardBtn = new Button("⏪︎");
        forwardBtn = new Button("⏩︎");
        previousBtn = new Button("⏮");
        nextBtn = new Button("⏭");
        shuffleBtn = new Button("\uD83D\uDD00");
        repeatBtn = new Button("\uD83D\uDD01");
        repeatOnceBtn = new Button("\uD83D\uDD02");
        infoBtn = new Button("ℹ️");

        // Button Actions
        playPauseBtn.setOnAction(e -> {
            if (isPlaying){
                playPauseBtn.setText("\u23F5");
                isPlaying = false;
                player.pause();
            }
            else {
                playPauseBtn.setText("️\u23F8");
                isPlaying = true;
                player.play();
            }
        });
        backwardBtn.setOnAction(e -> {
            player.decreaseVol();
        });
        forwardBtn.setOnAction(e -> {
            player.increaseVol();
        });

        //Layout 1
        HBox playerLayout = new HBox(10);
        playerLayout.getChildren().addAll(label1, button1, backwardBtn, playPauseBtn, StopBtn,
                forwardBtn, shuffleBtn, repeatBtn, repeatOnceBtn, infoBtn);
        scene1 = new Scene(playerLayout, 500,500);

        //Button 2
        Button button2 = new Button("Back to Player");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout = new StackPane();
        layout.getChildren().addAll(button2);
        scene2 = new Scene(layout, 500, 500);

        //Initializing window
        window.setScene(scene1);
        window.setTitle("Oh papa capim do meu sonho");
        window.show();
    }
}

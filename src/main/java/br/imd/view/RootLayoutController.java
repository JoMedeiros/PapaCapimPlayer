package br.imd.view;

import br.imd.Main;
import br.imd.model.Music;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {
    private Main main;
    @FXML
    AnchorPane userPane;

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Define um filtro de extensão
        FileChooser.ExtensionFilter extFilter = new FileChooser.
                ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostra a janela de salvar arquivo
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            // Do something to add music to music file
            // main.loadUserDataFromFile(file);
            // Instantiate a new Music object
            String URI = new File(file.getPath()).toURI().toString(); // Converting path to URI String format
            Music music = new Music(URI);
            System.out.println("Título depois de criada: " + music.getTitle());
            for (Music m : this.main.getMusicData()) {
                if (m.getPath() == music.getPath()) {
                    System.out.println("Música já inserida");
                    return;
                }
            }
            this.main.getMusicData().add(music);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

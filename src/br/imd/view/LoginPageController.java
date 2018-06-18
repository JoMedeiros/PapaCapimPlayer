package br.imd.view;

import br.imd.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
    @FXML
    TextField userField;
    @FXML
    PasswordField passwordField;

    Main main;

    /**
     * Login function.
     */
    public void handleLogin(){
        System.out.println("Logged! Yeey");
        System.out.println("Username: " + userField.getText());
        System.out.println("Senha: " + passwordField.getText());
        this.main.initRootLayout();
        this.main.showPlayer();
        this.main.showUser();
    }

    public void setMain(Main main){
        this.main = main;
    }
}

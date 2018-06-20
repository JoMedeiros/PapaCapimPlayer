package br.imd.view;

import br.imd.Main;
import br.imd.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class LoginPageController {
    @FXML
    TextField userField;
    @FXML
    PasswordField passwordField;

    /**
     * Logged in User
     */
    private User currentUser;

    Main main;

    /**
     * Login function.
     */
    public void handleLogin(){
        System.out.println("Username: " + userField.getText());
        System.out.println("Senha: " + passwordField.getText());

        User user = match(userField.getText());
        if (user == null) {
            System.out.println("Username dont match");
            return;
        }

        // Generating Hash
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte [] encadehash = algorithm.digest(
                    passwordField.getText().getBytes(StandardCharsets.UTF_8)
            );
            // Converting bytes to hexadecimal
            for (int i = 0; i < encadehash.length; i++) {
                String hex = Integer.toHexString(0xff & encadehash[i]);
                if (hex.length() == 1 ) hexString.append('0');
                hexString.append(hex);
            }
        } catch (Exception e) {
            System.out.println("No such algorithm");
        }
        // Verifying if hexString equals the stored hash password
        if (hexString.toString().equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")) {
            this.main.initRootLayout();
            this.main.showPlayer();
            this.main.showUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Password Don't Match");
            alert.setHeaderText("Username or password incorrect");
            alert.setContentText("You entered a invalid username or password don't match.\n");

            alert.showAndWait();
        }
    }

    public User match(String userName){
        for (User u : main.getUserData()){
            if (u.getId() == 0) {
                return u;
            }
        }
        return null;
    }

    /**
     * Sets reference to Main class
     * @param main
     */

    public void setMain(Main main){
        this.main = main;
    }
}

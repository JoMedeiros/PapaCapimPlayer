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
        User user = match(userField.getText());
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User not found");
            alert.setHeaderText("There's no such user");
            alert.setContentText("Please verify if you typed the correct user name.\n");

            alert.showAndWait();
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
        if (hexString.toString().equals(user.getPassword())) {
            this.main.initRootLayout();
            this.main.showPlayer();
            this.main.showUser();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Password");
            alert.setHeaderText("Password incorrect");
            alert.setContentText("You entered a invalid password.\n");

            alert.showAndWait();
        }
    }

    /**
     * A function to find an return the user corresponding to the userName entered in TextField
     * @param userName
     * @return
     */
    public User match(String userName){
        for (User u : main.getUserData()){
            if (u.getName().equals(userName)) {
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

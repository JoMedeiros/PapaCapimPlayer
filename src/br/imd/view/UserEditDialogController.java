package br.imd.view;

import br.imd.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;

public class UserEditDialogController {
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Checkbox isVIPCheckbox;

    private Stage dialogStage;

    private User user;

    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Create a user if all the fields are correctly fulfilled
     */
    public void handleRegister(){
        if (isInputValid()){
            // Display dialog saying that the passwords don't match
        }
        else {

        }
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (userNameField.getText() == null || userNameField.getText().length() == 0)
            errorMessage += "No valid user name\n";
        if (!confirmPasswordField.getText().equals(passwordField.getText()))
            errorMessage += "The passwords don't match\n";
        // @TODO verify if username alredy exists

        if (errorMessage.length() == 0){
            return true;
        } else {
            // @TODO show the error message.
            return false;
        }
    }
}

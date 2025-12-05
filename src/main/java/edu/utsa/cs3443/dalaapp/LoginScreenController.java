package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * controls the login screen
 */
public class LoginScreenController {
    private UserManager userManager;

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Label messageLabel;

    /**
     * initializes the login screen
     */
    @FXML
    public void initialize() {
        userManager = UserManager.getInstance();
        if (messageLabel != null) {
            messageLabel.setText("");
        }
    }

    /**
     * handles pressing username button
     * @param event event action event
     */
    @FXML
    private void usernameButton(ActionEvent event) {
        performLogin(event);
    }

    /**
     * handles pressing the password button
     * @param event action event
     */
    @FXML
    private void passwordButton(ActionEvent event) {
        performLogin(event);
    }

    /**
     * handles the login button
     * @param event action event
     */
    @FXML
    private void loginButtonClicked(ActionEvent event) {
        performLogin(event);
    }

    /**
     * performs the login and goes to the menu
     * @param event action event
     */
    private void performLogin(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username == null || username.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter a username.");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter a password.");
            return;
        }

        if (rememberMeCheckBox != null) {
            userManager.setRememberMe(rememberMeCheckBox.isSelected());
        }

        boolean success = userManager.login(username.trim(), password.trim());

        if (success) {
            if (messageLabel != null) messageLabel.setText("Login successful!");
            try {
                switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
            } catch (Exception e) {
                if (messageLabel != null) messageLabel.setText("Error loading menu: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            if (messageLabel != null) messageLabel.setText("Invalid username or password.");
            passwordField.clear();
        }
    }

    /**
     * opens the sign-up screen
     * @param event action event
     */
    @FXML
    private void onSignUpClicked(ActionEvent event) {
        try {
            switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/signup.fxml", "DALA — Sign Up");
        } catch (Exception e) {
            if (messageLabel != null) messageLabel.setText("Error loading signup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * handles the sign-up button
     * @param event action event
     */
    @FXML
    private void signUpButtonClicked(ActionEvent event) {
        onSignUpClicked(event);
    }

    /**
     * goes back to the welcome screen
     * @param event action event
     */
    @FXML
    private void onBackClicked(ActionEvent event) {
        try {
            switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/welcome.fxml", "DALA — Welcome");
        } catch (Exception e) {
            if (messageLabel != null) messageLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * switches to another screen
     * @param event action event
     * @param fxmlPath FXML file path
     * @param title window title
     * @throws Exception if the FXML cant be loaded
     */
    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}
package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls the logic behind the
 * signup.fxml screen view.
 */
public class SignUpScreenController {
    private UserManager userManager;

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    /**
     * Initializes the signup screen &
     * instantiates a new UserManager object.
     */
    @FXML
    public void initialize() {
        userManager = UserManager.getInstance();
        if (messageLabel != null) {
            messageLabel.setText("");
        }
    }

    /**
     * Focuses on the lastNameTextField
     * once interacted with.
     * @param event
     */
    @FXML
    private void firstNameButton(ActionEvent event) {
        lastNameTextField.requestFocus();
    }

    /**
     * Focuses on the emailTextField
     * once interacted with.
     * @param event
     */
    @FXML
    private void lastNameButton(ActionEvent event) {
        emailTextField.requestFocus();
    }

    /**
     * Focuses on the usernameTextField
     * once interacted with.
     * @param event
     */
    @FXML
    private void emailButton(ActionEvent event) {
        usernameTextField.requestFocus();
    }

    /**
     * Focuses on the passwordField once
     * interacted with.
     * @param event
     */
    @FXML
    private void usernameButton(ActionEvent event) {
        passwordField.requestFocus();
    }

    /**
     * When enter is pressed on the password
     * field, a new account is created.
     * @param event
     */
    @FXML
    private void passwordButton(ActionEvent event) {
        createAccount(event);
    }

    /**
     * utilizes the createAccount method
     * once the createAccount button is clicked
     * @param event
     */
    @FXML
    private void createAccountClicked(ActionEvent event) {
        createAccount(event);
    }

    /**
     * Switches the screen to the login.fxml screen
     * once the corresponding button is clicked.
     * @param event
     * @throws Exception
     */
    @FXML
    private void goBackButtonClicked(ActionEvent event) throws Exception{
        switchTo(event, "/layouts/login.fxml", "DALA - Login");
    }

    /**
     * Handles the logic for
     * creating a new user account.
     * @param event
     */
    private void createAccount(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (firstName == null || firstName.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter your first name.");
            return;
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter your last name.");
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter an email.");
            return;
        }

        if (!isValidEmail(email.trim())) {
            if (messageLabel != null) messageLabel.setText("Please enter a valid email address.");
            return;
        }

        if (username == null || username.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter a username.");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            if (messageLabel != null) messageLabel.setText("Please enter a password.");
            return;
        }

        if (password.length() < 6) {
            if (messageLabel != null) messageLabel.setText("Password must be at least 6 characters.");
            return;
        }

        if (userManager.userExists(username.trim())) {
            if (messageLabel != null) messageLabel.setText("Username already exists. Please choose another.");
            return;
        }

        boolean success = userManager.signup(
                firstName.trim(),
                lastName.trim(),
                email.trim(),
                username.trim(),
                password.trim()
        );

        if (success) {
            if (messageLabel != null) messageLabel.setText("Account created successfully!");
            try {
                Thread.sleep(1000);
                switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/login.fxml", "DALA — Login");
            } catch (Exception e) {
                if (messageLabel != null) messageLabel.setText("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            if (messageLabel != null) messageLabel.setText("Error creating account. Please try again.");
        }
    }

    /**
     * Checks the email text field to
     * make sure the input is valid.
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf("@") < email.lastIndexOf(".");
    }

    /**
     * Handles the logic for switching the
     * screen view.
     * @param event
     * @param fxmlPath
     * @param title
     * @throws Exception
     */
    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}
package edu.utsa.cs3443.dalaapp;

import com.idktogo.idk_to_go.core.Navigation;
import com.idktogo.idk_to_go.core.SessionManager;
import com.idktogo.idk_to_go.dao.UserDAO;
import com.idktogo.idk_to_go.data.AppStorage;
import com.idktogo.idk_to_go.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox rememberMeCheck;

    @FXML
    private void loginButtonClicked() {
        String username = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password.");
            return;
        }

        UserDAO.findByUsername(username)
                .thenAccept(optionalUser -> Platform.runLater(() -> {
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();

                        if (user.password().equals(password)) {

                            SessionManager.login(user.id(), user.username());

                            if (rememberMeCheck.isSelected()) {
                                AppStorage.save("rememberMe", "true");
                            } else {
                                AppStorage.remove("rememberMe");
                            }

                            showAlert("Welcome", "Login successful.");
                            Navigation.load("/com/idktogo/idk_to_go/main.fxml");
                        } else {
                            showAlert("Error", "Invalid username or password.");
                        }
                    } else {
                        showAlert("Error", "User not found.");
                    }
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> showAlert("Error", "Login failed: " + ex.getMessage()));
                    return null;
                });
    }

    @FXML
    private void signUpButtonClicked() { Navigation.load("/com/idktogo/idk_to_go/register.fxml"); }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
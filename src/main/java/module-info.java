module edu.utsa.cs3443.dalaapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utsa.cs3443.dalaapp to javafx.fxml;
    opens edu.utsa.cs3443.dalaapp.model to javafx.fxml;

    exports edu.utsa.cs3443.dalaapp;
}

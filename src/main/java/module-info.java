module edu.utsa.cs3443.dalaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.prefs;
    requires java.sql;

    opens edu.utsa.cs3443.dalaapp to javafx.fxml;
    opens edu.utsa.cs3443.dalaapp.model to javafx.fxml;

    exports edu.utsa.cs3443.dalaapp;
}

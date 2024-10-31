module info.university.components {
    requires javafx.controls;
    requires javafx.fxml;

    opens info.university.fxmlapplication to javafx.fxml;
    exports info.university.fxmlapplication;
}

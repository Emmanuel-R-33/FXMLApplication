module info.university.fxmlapplication.controllers {
    requires javafx.controls;
    requires javafx.fxml;

    opens info.university.fxmlapplication to javafx.fxml;
    exports info.university.fxmlapplication;
}

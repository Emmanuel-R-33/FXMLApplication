module info.university.fxmlapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    
    // Abrir el paquete principal para permitir la reflexión de FXMLLoader
    opens info.university.fxmlapplication to javafx.fxml;
    exports info.university.fxmlapplication;
    
    // Abrir el paquete de controladores para permitir la reflexión
    opens info.university.fxmlapplication.controllers to javafx.fxml;
    exports info.university.fxmlapplication.controllers;
    
    // Abrir los paquetes adicionales como components, dialogs, layouts, views, etc.
    //opens info.university.fxmlapplication.components to javafx.fxml;
    //exports info.university.fxmlapplication.components;
    
    //opens info.university.fxmlapplication.dialogs to javafx.fxml;
    //exports info.university.fxmlapplication.dialogs;
    
    //opens info.university.fxmlapplication.views to javafx.fxml;
    //exports info.university.fxmlapplication.views;
    
    //opens info.university.fxmlapplication.layouts to javafx.fxml;
    //exports info.university.fxmlapplication.layouts;
}

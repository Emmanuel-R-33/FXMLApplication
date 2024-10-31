package info.university.fxmlapplication.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<String> tableView;

    @FXML
    private Button registerButton;

    @FXML
    private void handleRegister() {
        // Aquí puedes manejar la lógica al hacer clic en el botón de registro
        String name = nameField.getText();
        String email = emailField.getText();
        System.out.println("Nombre: " + name + ", Correo: " + email);

        // Lógica para agregar los datos a la tabla o hacer otra operación
        // Ejemplo: actualizar la tabla o enviar datos a un servidor
    }
}
package com.example.smarthome.Controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class ErrorLoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnOk;

    @FXML
    void btnOkMethod(ActionEvent event) {
        btnOk.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {


    }


}

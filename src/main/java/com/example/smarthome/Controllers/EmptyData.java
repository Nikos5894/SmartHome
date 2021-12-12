package com.example.smarthome.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EmptyData {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnOk;


    @FXML
    void initialize() {
        btnOk.setOnAction(event -> {
            btnOk.getScene().getWindow().hide();
        });
    }

}

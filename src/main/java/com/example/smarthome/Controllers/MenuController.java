package com.example.smarthome.Controllers;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.smarthome.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDevice;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnRoom;

    @FXML
    private Button btnUserSettings;

    @FXML
    void btnDeviceMethod(ActionEvent event) {
        Main.SwitchScene("Device.fxml",btnDevice,true);
    }

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Login.fxml",btnGoBack,true);
    }

    @FXML
    void btnRoomMethod(ActionEvent event) {
        Main.SwitchScene("Room.fxml",btnRoom,true);
    }

    @FXML
    void btnUserSettingsMethod(ActionEvent event) {
        Main.SwitchScene("UserSetting.fxml",btnUserSettings,true);
    }
    @FXML
    void initialize() {

    }

}

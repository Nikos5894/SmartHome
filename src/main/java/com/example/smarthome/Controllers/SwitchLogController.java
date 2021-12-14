package com.example.smarthome.Controllers;


import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.DataBase.UserRepository;

import com.example.smarthome.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;


public class SwitchLogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAcceptSwitchLogin;

    @FXML
    private Button butSwitchLoginBack;

    @FXML
    private Text textSwitchLogError;

    @FXML
    private TextField tfNewLogin;

    @FXML
    private TextField tfNowLogin;



    @FXML
    private void initialize() {
        textSwitchLogError.setText(" ");

        butAcceptSwitchLogin.setOnAction(event -> {
            String oldLogin = tfNowLogin.getText().trim();
            String newLogin = tfNewLogin.getText().trim();
            if (!oldLogin.equals("")&& !newLogin.equals("")) {
                UserRepository ur = new UserRepository(DBConnection.URL);
                int count = 0;
                int res = 0;
                try {
                    count = ur.findUser(oldLogin);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(count == 1){
                    try {
                        res = ur.changeLogin(oldLogin,newLogin);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(res == 1){
                        textSwitchLogError.setText("Success");
                    }
                    else{
                        textSwitchLogError.setText("Wrong login");
                    }
                }
            }});

        butSwitchLoginBack.setOnAction(Event -> {
            Main.SwitchScene("UserSetting.fxml", butSwitchLoginBack,true);
        });
    }



}

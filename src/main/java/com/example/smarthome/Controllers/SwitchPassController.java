package com.example.smarthome.Controllers;


import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.DataBase.UserRepository;
import com.example.smarthome.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SwitchPassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAcceptSwitchPassword;

    @FXML
    private Button butSwitchPasswordBack;

    @FXML
    private Text textSwitchPassError;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfNewPassword;

    @FXML
    private PasswordField tfNowPassword;
    @FXML
    private void initialize() {
        textSwitchPassError.setText("");

        butAcceptSwitchPassword.setOnAction(Event -> {
            String login = tfLogin.getText().trim();
            String oldPassword = tfNowPassword.getText().trim();
            String newPassword = tfNewPassword.getText().trim();
            if(!login.equals("") && !oldPassword.equals("") && !newPassword.equals("")){
                UserRepository ur = new UserRepository(DBConnection.URL);
                int count = 0;
                int res = 0;
                try {
                    count = ur.findUser(login);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(count == 1){
                    try {
                        res = ur.changePassword(oldPassword,newPassword,login);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(res == 1){
                        textSwitchPassError.setText("Success");
                    }
                    else{
                        textSwitchPassError.setText("Wrong login or password");
                    }
                }
            }
        });
        butSwitchPasswordBack.setOnAction(event -> {
            Main.SwitchScene("UserSetting.fxml",butSwitchPasswordBack,true);
        });
    }



}

package com.example.smarthome.Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.Main;
import com.example.smarthome.DataBase.UserRepository;
import com.example.smarthome.Entity.UserTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpSurName;

    @FXML
    private Button btnSignup;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblErrors;

    @FXML
    private TextField signUpName;

    @FXML
    void signUpMethod(ActionEvent event) throws IOException {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();
            String firstName = signUpName.getText().trim();
            String lastName = signUpSurName.getText().trim();
            if(!loginText.equals("") && !passwordText.equals("")&& !firstName.equals("") && !lastName.equals("")){
                try {
                    signUpNewUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                btnSignup.getScene().getWindow().hide();
                Main.showLogin();
            }
            else {
               Main.showEmptyData();
            }



    }
    @FXML
    void initialize() throws IOException {

        btnCancel.setOnAction(event -> {
            Main.SwitchScene("Login.fxml",btnCancel,true);
        });

    }

    private void signUpNewUser() throws SQLException {

        UserRepository userRepository = new UserRepository(DBConnection.URL);

        //ConnectionDB con = new ConnectionDB();
        String firstName = signUpName.getText();
        String lastName = signUpSurName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();


        UserTable user = new UserTable(firstName,lastName,userName,password);

        userRepository.signUpUser(user);

    }

}

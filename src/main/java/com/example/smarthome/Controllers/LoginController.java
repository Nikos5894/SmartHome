package com.example.smarthome.Controllers;

import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.DataBase.UserRepository;
import com.example.smarthome.Main;
import com.example.smarthome.Entity.UserTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void SignInMethod(ActionEvent event) throws IOException {

            String loginText =  txtUsername.getText().trim();
            String passwordText = txtPassword.getText().trim();
            if(!loginText.equals("") && !passwordText.equals("")){
                try {
                    loginUser(loginText,passwordText);
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(!loginText.equals("") || !passwordText.equals("")){
                Main.SwitchScene("NotAllInputLogin.fxml",btnSignin,false);
            }
            else
                Main.SwitchScene("ErrorLogin.fxml",btnSignin,false);

    }

    @FXML
    void SignUpMethod(ActionEvent event) throws IOException {

        btnSignup.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();

        Parent root = FXMLLoader.load(Main.class.getResource("Registration.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("https://st4.depositphotos.com/1010683/30350/i/1600/depositphotos_303508190-stock-photo-finger-touch-on-the-tablet.jpg"));
        stage.setTitle("Sign up window!");
        stage.showAndWait();

    }

    private void loginUser(String loginText, String passwordText) throws SQLException, IOException {
        UserRepository userRepository = new UserRepository(DBConnection.URL);


        UserTable user = new UserTable();
        user.setUserName(loginText);
        user.setPassword(passwordText);
        int counter = userRepository.getUser(user);
        if(counter >= 1){
            btnSignup.getScene().getWindow().hide();

            Parent root = FXMLLoader.load(Main.class.getResource("Menu.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image("https://st4.depositphotos.com/1010683/30350/i/1600/depositphotos_303508190-stock-photo-finger-touch-on-the-tablet.jpg"));
            stage.setScene(new Scene(root));
            stage.setTitle("Menu window!");
            stage.showAndWait();
        }
        else{
            Main.SwitchScene("IncorrectLogin.fxml",btnSignup,false);
        }

    }

}

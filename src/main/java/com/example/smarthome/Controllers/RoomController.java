package com.example.smarthome.Controllers;


import com.example.smarthome.DataBase.RoomRepository;
import com.example.smarthome.Entity.Room;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController {

    private ObservableList<Room> roomData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddRoom;

    @FXML
    private Button btnDeleteRoom;

    @FXML
    private TableColumn<Room, Integer> countOfSocketsColumn;

    @FXML
    private TableColumn<Room, Integer> idColumn;

    @FXML
    private TableColumn<Room, String> nameColumn;

    @FXML
    private TableColumn<Room, Integer> squareMetersColumn;

    @FXML
    private TableView<Room> tableUsers;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnShowDevicesInRoom;





    @FXML
    private TextField tfRoom;

    @FXML
    void btnShowDevicesInRoomMethod(ActionEvent event) throws IOException {
        String id = tfRoom.getText();
        if(!id.equals("")) {
            DevicesInRoomController deviceInRoomController = new DevicesInRoomController(Integer.valueOf(tfRoom.getText()));
            btnShowDevicesInRoom.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DevicesInRoom.fxml"));
            fxmlLoader.setController(deviceInRoomController);
            try {
                fxmlLoader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Devices in room");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.getIcons().add(new Image("https://st4.depositphotos.com/1010683/30350/i/1600/depositphotos_303508190-stock-photo-finger-touch-on-the-tablet.jpg"));
            stage.setResizable(false);
            stage.show();
        }
        else{
            Main.showEmptyData();
        }
    }

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Menu.fxml",btnGoBack,true);
    }

    @FXML
    void btnAddRoomMethod(ActionEvent event) {
        Main.SwitchScene("AddRoom.fxml",btnAddRoom,true);
    }

    @FXML
    void btnDeleteRoomMethod(ActionEvent event) {
        Main.SwitchScene("DeleteRoom.fxml",btnDeleteRoom,true);
    }
    // инициализируем форму данными
    @FXML
    private void initialize() throws SQLException {

        RoomRepository rr = new RoomRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        List<Room> rooms =rr.getAllRooms();
        roomData.addAll(rooms);

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
        countOfSocketsColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("countOfSockets"));
        squareMetersColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("squareMeters"));

        // заполняем таблицу данными
        tableUsers.setItems(roomData);
    }


}
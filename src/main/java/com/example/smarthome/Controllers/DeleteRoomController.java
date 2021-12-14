package com.example.smarthome.Controllers;

import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.DataBase.RoomRepository;
import com.example.smarthome.Entity.Room;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteRoomController {
    private ObservableList<Room> roomData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField txtIdText;

    @FXML
    private Button btnGoBack;

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Room.fxml",btnGoBack,true);
    }

    @FXML
    void btnDeleteRoomMethod(ActionEvent event) throws IOException, SQLException {
        String Id = txtIdText.getText().trim();
        if(!Id.equals("")){
            deleteDevice();
            Main.SwitchScene("Room.fxml",btnDeleteRoom,true);
        }
        else{
            Main.showEmptyData();
        }

    }

    private void deleteDevice() throws SQLException {
        RoomRepository roomRepository = new RoomRepository(DBConnection.URL);
        int id = Integer.parseInt(txtIdText.getText());
        roomRepository.delRoom(id);
    }

    @FXML
    void initialize() throws SQLException {

        RoomRepository rr = new RoomRepository(DBConnection.URL);
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

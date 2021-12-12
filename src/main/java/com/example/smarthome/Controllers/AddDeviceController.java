package com.example.smarthome.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.smarthome.DataBase.DeviceRepository;
import com.example.smarthome.DataBase.RoomRepository;
import com.example.smarthome.Entity.Device;
import com.example.smarthome.Entity.Room;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddDeviceController {

    private final ObservableList<Room> roomList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtCapacity;

    @FXML
    private Button btnGoBack;

    @FXML
    private ComboBox<Room> RoomList;

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Device.fxml",btnGoBack,true);
    }

    @FXML
    void btnAddMethod(ActionEvent event) throws IOException {

        String name = txtName.getText().trim();
        String model = txtModel.getText().trim();
        String capacity = txtCapacity.getText().trim();

        if(!name.equals("") && !model.equals("")&& !capacity.equals("")){
            try {
                addDevice();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
            Main.showEmptyData();
        }

    }

    private void addDevice() throws SQLException {
        DeviceRepository deviceRepository = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");

        //ConnectionDB con = new ConnectionDB();
        String name = txtName.getText();
        String model = txtModel.getText();
        int capacity = Integer.parseInt(txtCapacity.getText());

        Room room = RoomList.getValue();
        int roomId = room.getId();

        String isConnected = "No";
        Device device = new Device(name,model,capacity,isConnected,roomId);

        deviceRepository.addDevice(device);
    }

    @FXML
    void initialize() throws SQLException {
        RoomRepository rr = new RoomRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");

        List<Room> rooms = rr.getAllRooms();
        roomList.addAll(rooms);

        RoomList.setItems(roomList);
        RoomList.setId("id");

    }

}
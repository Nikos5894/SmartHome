package com.example.smarthome.Controllers;

import com.example.smarthome.DataBase.DBConnection;
import com.example.smarthome.DataBase.DeviceRepository;
import com.example.smarthome.Entity.Device;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class DevicesInRangeController {

    private ObservableList<Device> deviceData = FXCollections.observableArrayList();

    private int min;
    private int max;

    public DevicesInRangeController() {
    }

    public DevicesInRangeController(int min, int max) {
        this.min = min;
        this.max = max;
    }


    @FXML
    private Button btnGoBack;

    @FXML
    private TableColumn<Device, Integer> capacityColumn;

    @FXML
    private TableColumn<Device, Integer> idColumn;

    @FXML
    private TableColumn<Device, String> modelColumn;

    @FXML
    private TableColumn<Device, String> nameColumn;

    @FXML
    private TableColumn<Device, String> connectColumn;

    @FXML
    private TableColumn<Device, Integer> roomColumn;

    @FXML
    private TableView<Device> tableUsers;

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Device.fxml",btnGoBack,true);
    }
    @FXML

    private void initialize() throws SQLException {
        DeviceRepository dr = new DeviceRepository(DBConnection.URL);
        List<Device> devices = dr.getDevicesInRange(min,max);
        deviceData.addAll(devices);

        idColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("model"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("capacity"));
        connectColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("connected"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("roomId"));

        tableUsers.setItems(deviceData);
    }
}

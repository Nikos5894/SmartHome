package com.example.smarthome.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteDeviceController {
    private ObservableList<Device> deviceData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

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
    private TextField txtIdText;

    @FXML
    private Button btnGoBack;

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Device.fxml",btnGoBack,true);
    }

    @FXML
    void btnDeleteMethod(ActionEvent event) throws IOException, SQLException {
        String Id = txtIdText.getText().trim();
        if(!Id.equals("")){
            deleteDevice();
            Main.SwitchScene("Device.fxml",btnDelete,true);
        }
        else{
            Main.showEmptyData();
        }

    }

    private void deleteDevice() throws SQLException {
        DeviceRepository deviceRepository = new DeviceRepository(DBConnection.URL);
        int id = Integer.parseInt(txtIdText.getText());
        deviceRepository.delDevice(id);
    }

    @FXML
    void initialize() throws SQLException {
        DeviceRepository dr = new DeviceRepository(DBConnection.URL);
        List<Device> devices = dr.getDevices();
        deviceData.addAll(devices);
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("model"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("capacity"));
        connectColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("connected"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("roomId"));



        // заполняем таблицу данными
        tableUsers.setItems(deviceData);

    }

}

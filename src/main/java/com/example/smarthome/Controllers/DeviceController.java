package com.example.smarthome.Controllers;


import com.example.smarthome.DataBase.DeviceRepository;
import com.example.smarthome.Entity.Device;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DeviceController {

    private ObservableList<Device> deviceData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button btnAdd;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFindCapacity;

    @FXML
    private Button btnFindInRange;

    @FXML
    private Button btnGoBack;

    @FXML
    private TextField tfConnect;

    @FXML
    private Button butRefresh;

    @FXML
    private TextField tfSumma;

    @FXML
    void btnConnectMethod(ActionEvent event) throws SQLException, IOException {
        DeviceRepository deviceRepository = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        String id = tfConnect.getText();
        if(!id.equals("")) {
            deviceRepository.updateDevice("Yes", Integer.parseInt(tfConnect.getText()));
        }
        else{
            Main.showEmptyData();
        }

    }

    @FXML
    void btnFindCapacityMethod(ActionEvent event) {
        Integer sum = 0;
        for (int i = 0; i < deviceData.size(); i++){
            sum+=deviceData.get(i).getCapacity();
        }

        tfSumma.setText(sum.toString());
    }

    @FXML
    void btnFindInRangeMethod(ActionEvent event) {
        Main.SwitchScene("SearchRange.fxml",btnFindInRange,true);
    }

    @FXML
    void btnAddMethod(ActionEvent event) {
        Main.SwitchScene("AddDevice.fxml",btnAdd,true);
    }

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Menu.fxml",btnGoBack,true);
    }
    @FXML
    void btnDeleteMethod(ActionEvent event) {
        Main.SwitchScene("DeleteDevice.fxml",btnDelete,true);
    }
    // ініціалізуємо форму даними
    @FXML
    private void initialize() throws SQLException {

        DeviceRepository dr = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        List<Device> devices = dr.getDevices();
        deviceData.addAll(devices);

        // встановлюємо тип і значення яке повинно міститися в колонці
        idColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("model"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("capacity"));
        connectColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("connected"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("roomId"));

        // заповнюємо таблицю даними
        tableUsers.setItems(deviceData);

        butRefresh.setOnAction(event -> {
            Main.SwitchScene("Device.fxml",butRefresh,true);
        });
    }

}
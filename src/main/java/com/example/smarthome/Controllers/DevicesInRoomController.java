package com.example.smarthome.Controllers;

import com.example.smarthome.DataBase.DeviceRepository;
import com.example.smarthome.Entity.Device;
import com.example.smarthome.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DevicesInRoomController {

    private ObservableList<Device> deviceData = FXCollections.observableArrayList();

    private int id;

    public DevicesInRoomController() {
    }

    public DevicesInRoomController(int id) {
       this.id = id;
    }

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnDisconnect;

    @FXML
    private Button butRefresh;

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
    private TextField tfConnect;

    @FXML
    private TextField tfDisconnect;

    @FXML
    void btnConnectMethod(ActionEvent event) throws SQLException {
        DeviceRepository deviceRepository = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        int id = Integer.parseInt(tfConnect.getText());
        deviceRepository.updateDevice("Yes",id);
    }

    @FXML
    void btnDisconnectMethod(ActionEvent event) throws SQLException {
        DeviceRepository deviceRepository = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        int id = Integer.parseInt(tfDisconnect.getText());
        deviceRepository.updateDevice("No",id);
    }

    @FXML
    void btnGoBackMethod(ActionEvent event) {
        Main.SwitchScene("Room.fxml",btnGoBack, true);
    }
    @FXML
    private void initialize() throws SQLException {
        DeviceRepository dr = new DeviceRepository("jdbc:sqlserver://localhost:1433;database=SmartHome;user=Nikos5894;password=123455");
        List<Device> devices = dr.getDevicesInRoom(id);
        deviceData.addAll(devices);

        idColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("model"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("capacity"));
        connectColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("connected"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("roomId"));

        tableUsers.setItems(deviceData);

        butRefresh.setOnAction(event -> {
            DevicesInRoomController deviceInRoomController = new DevicesInRoomController(id);
            butRefresh.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DevicesInRoom.fxml"));
            fxmlLoader.setController(deviceInRoomController);
            try {
                fxmlLoader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Devices in Room");
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.getIcons().add(new Image("https://st4.depositphotos.com/1010683/30350/i/1600/depositphotos_303508190-stock-photo-finger-touch-on-the-tablet.jpg"));
            stage.setResizable(false);
            stage.show();
        });
    }
}
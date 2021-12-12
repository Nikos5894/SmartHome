package com.example.smarthome.DataBase;

import com.example.smarthome.Entity.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceRepository {

    private final String url;
    private Connection con = null;

    public DeviceRepository(String url) {
        this.url = url;
    }

    public  void addDevice(Device device) throws SQLException {
        String insert = "INSERT INTO Device (name,model,capacity,connected,roomId) VALUES(?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url)) {
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setString(1, device.getName());
                pst.setString(2, device.getModel());
                pst.setInt(3, device.getCapacity());
                pst.setString(4, device.getConnected());
                pst.setInt(5, device.getRoomId());
                pst.execute();
            }
        }
    }
    public List<Device> getDevices()throws SQLException {
        String select = "SELECT * FROM Device ";
        try(Connection con = DriverManager.getConnection(url)){
            try(PreparedStatement pst = con.prepareStatement(select)){
                try(ResultSet resSet = pst.executeQuery()){
                    List<Device> devices = new ArrayList<Device>();
                    while (resSet.next()){
                        Device device = new Device(
                                resSet.getInt("id"),
                                resSet.getString("name"),
                                resSet.getString("model"),
                                resSet.getInt("capacity"),
                                resSet.getString("connected"),
                                resSet.getInt("roomId"));
                        devices.add(device);
                    }
                    return devices;
                }
            }
        }
    }
    public List<Device> getDevicesInRange(int min,int max)throws SQLException {
        String select = "SELECT * FROM Device where capacity between ? and ?";
        try(Connection con = DriverManager.getConnection(url)){
            try(PreparedStatement pst = con.prepareStatement(select)){
                pst.setInt(1,min);
                pst.setInt(2,max);
                try(ResultSet resSet = pst.executeQuery()){
                    List<Device> devices = new ArrayList<Device>();
                    while (resSet.next()){
                        Device device = new Device(
                                resSet.getInt("id"),
                                resSet.getString("name"),
                                resSet.getString("model"),
                                resSet.getInt("capacity"),
                                resSet.getString("connected"),
                                resSet.getInt("roomId"));
                        devices.add(device);
                    }
                    return devices;
                }
            }
        }
    }
    public List<Device> getDevicesInRoom(int id)throws SQLException {
        String select = "SELECT * FROM Device where roomId = ?";
        try(Connection con = DriverManager.getConnection(url)){
            try(PreparedStatement pst = con.prepareStatement(select)){
                pst.setInt(1,id);
                try(ResultSet resSet = pst.executeQuery()){
                    List<Device> devices = new ArrayList<Device>();
                    while (resSet.next()){
                        Device device = new Device(
                                resSet.getInt("id"),
                                resSet.getString("name"),
                                resSet.getString("model"),
                                resSet.getInt("capacity"),
                                resSet.getString("connected"),
                                resSet.getInt("roomId"));
                        devices.add(device);
                    }
                    return devices;
                }
            }
        }
    }
    public void updateDevice(String connection,int id) throws SQLException {
        String update = "UPDATE Device SET connected = ? where id = ?";
        try (Connection con = DriverManager.getConnection(url)) {
            try (PreparedStatement pst = con.prepareStatement(update)) {
                pst.setString(1,connection);
                pst.setInt(2, id);
                pst.execute();
            }
        }
    }
    public void delDevice(int id) throws SQLException {
        String delete = "DELETE FROM Device WHERE id =? ";
        try (Connection con = DriverManager.getConnection(url)) {
            try (PreparedStatement pst = con.prepareStatement(delete)) {
                pst.setInt(1, id);
                pst.execute();
            }
        }
    }
}

package com.example.smarthome.DataBase;

import com.example.smarthome.Entity.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private final String url;
    private Connection con = null;

    public RoomRepository(String url) {
        this.url = url;
    }

    public  void addRoom(Room room) throws SQLException {
        try (Connection con = DriverManager.getConnection(url)) {
            String insert = "INSERT INTO Room (name,countOfSockets,squareMeters) VALUES(?,?,?)";
            try (PreparedStatement pst = con.prepareStatement(insert)) {
                pst.setString(1, room.getName());
                pst.setInt(2, room.getCountOfSockets());
                pst.setInt(3, room.getSquareMeters());
                pst.execute();
            }
        }
    }


    public List<Room> getAllRooms() throws SQLException {
        String select = "SELECT id,name,countOfSockets,squareMeters FROM Room";
        try (Connection con = DriverManager.getConnection(url)) {
            try (PreparedStatement pst = con.prepareStatement(select)) {
                try (ResultSet resSet = pst.executeQuery()) {
                    List<Room> rooms = new ArrayList<Room>();
                    while (resSet.next()) {
                        Room room = new Room(
                                resSet.getInt("id"),
                                resSet.getString("name"),
                                resSet.getInt("countOfSockets"),
                                resSet.getInt("squareMeters"));
                        rooms.add(room);
                    }
                    return rooms;
                }
            }
        }
    }

    public void delRoom(int id) throws SQLException {
        String delete = "DELETE FROM Room WHERE id =? ";

        try (Connection con = DriverManager.getConnection(url)) {
            try (PreparedStatement pst = con.prepareStatement(delete)) {
                pst.setInt(1, id);
                pst.execute();
            }
        }
    }
}

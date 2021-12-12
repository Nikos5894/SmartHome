package com.example.smarthome.Entity;
public class Device {
    private int id;
    private String name;
    private String model;
    private int capacity;
    private String isConnected;
    private Integer roomId;
    public Device() {
    }

    public Device(String name, String model, int capacity,String isConnected,Integer roomId) {
        this.name = name;
        this.model = model;
        this.capacity = capacity;
        this.isConnected = isConnected;
        this.roomId = roomId;
    }

    public Device(int id, String name, String model, int capacity,String isConnected,Integer roomId) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.capacity = capacity;
        this.isConnected = isConnected;
        this.roomId = roomId;
    }

    public String getConnected() {
        return isConnected;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }


    public void setIsConnected(String connected) {
        isConnected = connected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

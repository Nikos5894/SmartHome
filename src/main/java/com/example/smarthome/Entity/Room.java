package com.example.smarthome.Entity;

public class Room {
    private int id;
    private String name;
    private int countOfSockets;
    private int squareMeters;

    public Room() {
    }

    public Room( String name, int countOfSockets, int squareMeters) {

        this.name = name;
        this.countOfSockets = countOfSockets;
        this.squareMeters = squareMeters;
    }

    public Room(int id, String name, int countOfSockets, int squareMeters) {
        this.id = id;
        this.name = name;
        this.countOfSockets = countOfSockets;
        this.squareMeters = squareMeters;
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

    public int getCountOfSockets() {
        return countOfSockets;
    }

    public void setCountOfSockets(int countOfSockets) {
        this.countOfSockets = countOfSockets;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squaremeters) {
        this.squareMeters = squaremeters;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

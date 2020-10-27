package com.example.finisheddeviceservice;

import lombok.Data;

@Data
public class Repairer {
    private Long id;
    private String username;
    private String name;
    private int idOfRoom;
    private int price;
    private String phone;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public int getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}

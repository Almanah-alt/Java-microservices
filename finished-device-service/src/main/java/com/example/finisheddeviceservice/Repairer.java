package com.example.finisheddeviceservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repairer {
    private Long id;
    private String username;
    private String name;
    private int idOfRoom;
    private int price;
    private String phone;
    private String password;

    public Repairer() {
    }

    public Repairer(Long id, String username, String name, int idOfRoom, int price, String phone, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.idOfRoom = idOfRoom;
        this.price = price;
        this.phone = phone;
        this.password = password;
    }
}

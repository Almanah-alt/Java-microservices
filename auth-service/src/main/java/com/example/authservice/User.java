package com.example.authservice;


import com.example.authservice.security.UserRole;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private int idOfRoom;
    private int price;
    @Column(unique = true)
    private String phone;
    private String password;
    private UserRole role;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

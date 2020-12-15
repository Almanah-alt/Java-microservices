package com.example.userservice;

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
}

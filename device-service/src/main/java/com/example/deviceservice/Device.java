package com.example.deviceservice;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String year;
    private String manufacturer;
    private String explanation;
    private String deviceOwnerPhone;
    private String deviceOwnerName;
    @Enumerated(EnumType.STRING)
    private Status status;
}


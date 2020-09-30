package com.example.finisheddeviceservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class FinishedDevices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Boolean isTaken;
    private Long deviceId;
    private String year;
    private String manufacturer;
    private String explanation;
    private String deviceOwnerPhone;
    private String deviceOwnerName;
    private String name;
    private Status status;
    private String phone;


}

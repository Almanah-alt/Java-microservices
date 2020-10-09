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

    public void setDeviceOwnerName(String deviceOwnerName) {
        this.deviceOwnerName = deviceOwnerName;
    }

    public void setDeviceOwnerPhone(String deviceOwnerPhone) {
        this.deviceOwnerPhone = deviceOwnerPhone;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


package com.example.finisheddeviceservice;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Device {
    private Long id;
    private String year;
    private String manufacturer;
    private String explanation;
    private String deviceOwnerPhone;
    private String deviceOwnerName;
    private Status status;

    public Device(){

    }

    public Device(Long id, String year, String manufacturer, String explanation, String deviceOwnerPhone, String deviceOwnerName, Status status) {
        this.id = id;
        this.year = year;
        this.manufacturer = manufacturer;
        this.explanation = explanation;
        this.deviceOwnerPhone = deviceOwnerPhone;
        this.deviceOwnerName = deviceOwnerName;
        this.status = status;
    }
}


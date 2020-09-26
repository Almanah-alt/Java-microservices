package com.example.finisheddeviceservice;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public Device() {
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

enum Status{
  Finished, Taken
}

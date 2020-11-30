package com.example.devicerequest;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FinishedDevices {

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

    public FinishedDevices(Long id, LocalDate date, Boolean isTaken, Long deviceId, String year, String manufacturer, String explanation, String deviceOwnerPhone, String deviceOwnerName, String name, Status status, String phone) {
        this.id = id;
        this.date = date;
        this.isTaken = isTaken;
        this.deviceId = deviceId;
        this.year = year;
        this.manufacturer = manufacturer;
        this.explanation = explanation;
        this.deviceOwnerPhone = deviceOwnerPhone;
        this.deviceOwnerName = deviceOwnerName;
        this.name = name;
        this.status = status;
        this.phone = phone;
    }
}

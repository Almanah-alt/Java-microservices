package com.example.finisheddeviceservice;

import lombok.Data;

@Data
public class Device {
    private Long id;
    private String year;
    private String manufacturer;
    private String explanation;
    private String deviceOwnerPhone;
    private String deviceOwnerName;
    private Status status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setDeviceOwnerPhone(String deviceOwnerPhone) {
        this.deviceOwnerPhone = deviceOwnerPhone;
    }

    public void setDeviceOwnerName(String deviceOwnerName) {
        this.deviceOwnerName = deviceOwnerName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDeviceOwnerPhone() {
        return deviceOwnerPhone;
    }

    public String getDeviceOwnerName() {
        return deviceOwnerName;
    }

    public Status getStatus() {
        return status;
    }
}


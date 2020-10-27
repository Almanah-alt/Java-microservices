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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setIsTaken(Boolean taken) {
        isTaken = taken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDeviceOwnerPhone() {
        return deviceOwnerPhone;
    }

    public void setDeviceOwnerPhone(String deviceOwnerPhone) {
        this.deviceOwnerPhone = deviceOwnerPhone;
    }

    public String getDeviceOwnerName() {
        return deviceOwnerName;
    }

    public void setDeviceOwnerName(String deviceOwnerName) {
        this.deviceOwnerName = deviceOwnerName;
    }


    public Status getStatus() {
        return status;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

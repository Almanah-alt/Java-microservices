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

}


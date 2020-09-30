package com.example.finisheddeviceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceService {

    @Autowired
    private RestTemplate restTemplate;

    public Device getDevice(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8083/api/devices/" + id,
                Device.class);
    }

    public void setStat(Long id) {
        restTemplate.getForObject(
                "http://localhost:8083/api/devices/setStatus/" + id,
                Device.class);
    }

    public void setStatTaken(Long id) {
        restTemplate.getForObject(
                "http://localhost:8083/api/devices/setStatusTaken/" + id,
                Device.class);
    }
}

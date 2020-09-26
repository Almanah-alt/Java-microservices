package com.example.finisheddeviceservice.service;

import com.example.finisheddeviceservice.Device;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getDeviceFallback",
            threadPoolKey = "getDevice",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Device getDevice(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8083/api/devices/" + id,
                Device.class);
    }

    @HystrixCommand(
            fallbackMethod = "getDeviceFallback",
            threadPoolKey = "getDevice",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public void setStat(Long id) {
        restTemplate.getForObject(
                "http://localhost:8083/api/devices/setStatus/" + id,
                Device.class);
    }

    @HystrixCommand(
            fallbackMethod = "getDeviceFallback",
            threadPoolKey = "getDevice",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public void setStatTaken(Long id) {
        restTemplate.getForObject(
                "http://localhost:8083/api/devices/setStatusTaken/" + id,
                Device.class);
    }
}

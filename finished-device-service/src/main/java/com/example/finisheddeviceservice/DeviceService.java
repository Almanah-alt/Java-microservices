package com.example.finisheddeviceservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMethodDevice",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            })
    public Device getDevice(Long id) {
        return restTemplate.getForObject(
                "http://device-service/api/device/" + id,
                Device.class);
    }

    public Device getFallbackMethodDevice(Long id) {
        Device device = new Device();
        device.setId(id);
        device.setDeviceOwnerName("Name not found");
        device.setDeviceOwnerPhone("Phone not found");
        device.setExplanation("No explanation");
        device.setManufacturer("No manufacturer");
        device.setStatus(Status.New);
        device.setYear("0");

        return device;
    }

    @HystrixCommand(fallbackMethod = "getFallbackMethodSetStatus",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            })
    public void setStat(Long id) {
        restTemplate.getForObject(
                "http://device-service/api/device/setStatus/" + id,
                Device.class);
    }

    public void getFallbackMethodSetStatus(Long id) {
        System.out.println("Not found device with id = " + id);
    }

    @HystrixCommand(fallbackMethod = "getFallbackMethodSetStatTaken",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            })
    public void setStatTaken(Long id) {
        restTemplate.getForObject(
                "http://device-service/api/device/setStatusTaken/" + id,
                Device.class);
    }

    public void getFallbackMethodSetStatTaken(Long id) {
        System.out.println("Not found device with id = " + id);
    }
}

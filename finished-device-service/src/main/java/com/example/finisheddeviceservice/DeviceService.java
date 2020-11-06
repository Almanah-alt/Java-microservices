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

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodDevice",
                threadPoolKey = "getDevicePool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
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

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodSetStatus",
                threadPoolKey = "setStatPool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
    public void setStat(Long id) {
        restTemplate.getForObject(
                "http://device-service/api/device/setStatus/" + id,
                Device.class);
    }

    public void getFallbackMethodSetStatus(Long id) {
        System.out.println("Not found device with id = " + id);
    }

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodSetStatTaken",
                threadPoolKey = "setStatTakenPool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
    public void setStatTaken(Long id) {
        restTemplate.getForObject(
                "http://device-service/api/device/setStatusTaken/" + id,
                Device.class);
    }

    public void getFallbackMethodSetStatTaken(Long id) {
        System.out.println("Not found device with id = " + id);
    }
}

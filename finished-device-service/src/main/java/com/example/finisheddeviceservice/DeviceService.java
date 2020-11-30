package com.example.finisheddeviceservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "http://device-service/api/device/" + id,
                HttpMethod.GET,
                entity,
                Device.class).getBody();
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

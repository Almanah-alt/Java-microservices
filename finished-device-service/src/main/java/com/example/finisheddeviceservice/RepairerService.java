package com.example.finisheddeviceservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RepairerService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodRepairer",
                threadPoolKey = "getDeviceRepairerPool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
    public Repairer getRepairer(Long id) {
        return restTemplate.getForObject(
                "http://repairer-service/api/repairer/byId/" + id,
                Repairer.class);
    }

    public Repairer getFallbackMethodRepairer(Long id) {
        Repairer repairer = new Repairer();
        repairer.setId(id);
        repairer.setIdOfRoom(0);
        repairer.setName("No name");
        repairer.setPassword("No Password");
        repairer.setPhone("No phone");
        repairer.setPrice(0);
        repairer.setUsername("No username");
        return repairer;
    }
}

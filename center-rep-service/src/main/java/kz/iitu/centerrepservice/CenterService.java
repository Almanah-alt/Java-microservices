package kz.iitu.centerrepservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CenterService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMethodCenter",
                    commandProperties = {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
                        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
                    })
    public Center getCenter(Long id) {
        return restTemplate.getForObject(
                "http://center-service/api/center/" + id,
                Center.class);
    }

    public Center getFallbackMethodCenter(Long id) {
        Center center = new Center();
        center.setId(id);
        center.setName("No Name");
        center.setLocation("No Location");
        return center;
    }

}

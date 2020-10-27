package kz.iitu.centerrepservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CenterService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMethodCenter")
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

package kz.iitu.centerrepservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CenterService {

    @Autowired
    private RestTemplate restTemplate;

    public Center getCenter(Long id) {
        return restTemplate.getForObject(
                "http://center-service/api/center/" + id,
                Center.class);
    }

}

package com.example.finisheddeviceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RepairerService {

    @Autowired
    private RestTemplate restTemplate;


    public Repairer getRepairer(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8081/api/repairer/byId/" + id,
                Repairer.class);
    }
}

package com.example.devicerequest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Base64;

@Service
public class FinishedDeviceInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getFinishedDeviceByIdFallback",
            threadPoolKey = "getFinishedDevices",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            }
    )
    public FinishedDevices getFinishedDeviceById(Long id) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        return restTemplate.exchange("http://finished-device-service/api/finishedDevices/" + id,
                HttpMethod.GET, entity, FinishedDevices.class).getBody();
    }

    public FinishedDevices getFinishedDeviceByIdFallback(Long id) {
        return new FinishedDevices(id, LocalDate.now(), false, 0L,"0", "null",
                "no", "no", "no name", "no name", Status.Finished, "no phone" );
    }
}
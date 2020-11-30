package kz.iitu.centerrepservice;

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
public class CenterService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodCenter",
                threadPoolKey = "getCenterPool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
    public Center getCenter(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        return restTemplate.exchange(
                "http://center-service/api/center/" + id,
                HttpMethod.GET,
                entity,
                Center.class).getBody();
    }

    public Center getFallbackMethodCenter(Long id) {
        Center center = new Center();
        center.setId(id);
        center.setName("No Name");
        center.setLocation("No Location");
        return center;
    }

}

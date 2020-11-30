package kz.iitu.centerrepservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RepairerService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
                fallbackMethod = "getFallbackMethodRepairer",
                threadPoolKey = "getCenterRepairerPool",
                threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
                }
            )
    public Repairer getRepairer(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "http://user-service/api/user/byId/" + id,
                HttpMethod.GET,
                entity,
                Repairer.class).getBody();
    }

    public Repairer getFallbackMethodRepairer(Long id) {
        System.out.println("occured");
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

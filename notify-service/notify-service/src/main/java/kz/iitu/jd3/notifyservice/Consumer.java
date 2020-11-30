package kz.iitu.jd3.notifyservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {


    @KafkaListener(topics = "finished_device_requests", groupId = "group_id")
    public void consume(FinishedDeviceRequest finishedDeviceRequest) throws IOException {
        System.out.println(String.format("#### -> Notify user by email: -> %s",
                "User " + finishedDeviceRequest.getUserId() + " taken device "
                        + finishedDeviceRequest.getFinishedDevices().toString()));
    }
}
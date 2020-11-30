package com.example.devicerequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "finished_device_requests";

    @Autowired
    private KafkaTemplate<String, FinishedDeviceRequest> kafkaTemplate;

    public String finishedDeviceRequestNotify(FinishedDeviceRequest finishedDeviceRequest) {
        System.out.println(String.format("#### -> Producing device request to notification service -> %s", finishedDeviceRequest));
        this.kafkaTemplate.send(TOPIC, finishedDeviceRequest);
        return "Successfully";
    }
}
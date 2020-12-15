package com.example.devicerequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/finishedDevice/request")
public class DeviceRequestController {

    private final Producer producer;
    private FinishedDeviceInfoService finishedDeviceInfoService;

    @Autowired
    public DeviceRequestController(Producer producer, FinishedDeviceInfoService finishedDeviceInfoService) {
        this.producer = producer;
        this.finishedDeviceInfoService = finishedDeviceInfoService;
    }


    // TODO Ideally there should POST request
    @GetMapping("{userId}/{finishedDeviceId}")
    public String sendMessageToKafkaTopic2(@PathVariable String userId,
                                           @PathVariable Long finishedDeviceId){

        FinishedDeviceRequest finishedDeviceRequest = new FinishedDeviceRequest(userId, finishedDeviceInfoService.getFinishedDeviceById(finishedDeviceId));
        this.producer.finishedDeviceRequestNotify(finishedDeviceRequest);
        return "Your request sent successful!";
    }
}
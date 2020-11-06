package com.example.finisheddeviceservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/finishedDevices")
public class FinishedDeviceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RepairerService repairerService;

    @Autowired
    private FinishedDeviceRepository finishedDeviceRepository;

    @HystrixCommand
    @GetMapping("")
    public List<FinishedDevices> repairCenterList(){
        return finishedDeviceRepository.findAll();
    }

    @PostMapping("/{deviceId}/{repId}")
    public void endOfRepairing(@PathVariable Long deviceId, @PathVariable Long repId){
        LocalDate date = LocalDate.now();
        Device device = deviceService.getDevice(deviceId);
        Repairer repairer = repairerService.getRepairer(repId);
        FinishedDevices finishedDevices = new FinishedDevices();
        if (!repairer.getUsername().equals("No username")){
            if (!device.getDeviceOwnerName().equals("Name not found")){
                finishedDevices.setName(repairer.getName());
                finishedDevices.setDate(date);
                finishedDevices.setIsTaken(false);
                finishedDevices.setDeviceId(device.getId());
                finishedDevices.setYear(device.getYear());
                finishedDevices.setPhone(repairer.getPhone());
                finishedDevices.setDeviceOwnerName(device.getDeviceOwnerName());
                finishedDevices.setDeviceOwnerPhone(device.getDeviceOwnerPhone());
                finishedDevices.setExplanation(device.getExplanation());
                finishedDevices.setManufacturer(device.getManufacturer());
                finishedDevices.setStatus(Status.Finished);
                finishedDeviceRepository.save(finishedDevices);
                deviceService.setStat(deviceId);
            }
        }


    }


    @PostMapping("/{id}")
    public void takeFinishedDevice(@PathVariable("id") Long id){
        FinishedDevices finishedDevices = finishedDeviceRepository.findById(id).get();
       finishedDevices.setStatus(Status.Taken);
       finishedDevices.setIsTaken(true);
       deviceService.setStatTaken(finishedDevices.getDeviceId());
       finishedDeviceRepository.saveAndFlush(finishedDevices);


    }


}

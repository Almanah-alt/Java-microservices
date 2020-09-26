package com.example.deviceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("")
    public List<Device> deviceList(){
        return deviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable("id") Long id) {
        return deviceRepository.findById(id).get();
    }

    @GetMapping("/setStatus/{id}")
    public void setStat(@PathVariable Long id){
         Device device = deviceRepository.findById(id).get();
         device.setStatus(Status.Finished);
         deviceRepository.save(device);
    }

    @GetMapping("/setStatusTaken/{id}")
    public void setStatTaken(@PathVariable Long id){
         Device device = deviceRepository.findById(id).get();
         device.setStatus(Status.Taken);
         deviceRepository.save(device);
    }

    @PostMapping("")
    public Device newDevice(@RequestBody Device device){
        if (device.getDeviceOwnerName().isEmpty() || device.getExplanation().isEmpty()){
            throw new RuntimeException("fields of device must be filled");
        }
        deviceRepository.save(device);
        return device;
    }

}

package com.example.deviceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("")
    public List<Device> deviceList(){
        return deviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
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

    @PostMapping("/{owner}/{phone}/{explanation}/{manuf}/{year}")
    public void newDevice(@PathVariable String owner, @PathVariable String phone, @PathVariable String explanation, @PathVariable String manuf, @PathVariable String year){
        if (owner.isEmpty() || phone.isEmpty() || explanation.isEmpty()){
            throw new RuntimeException("fields of device must be filled");
        }
        Device device = new Device();
        device.setDeviceOwnerName(owner);
        device.setDeviceOwnerPhone(phone);
        device.setExplanation(explanation);
        device.setManufacturer(manuf);
        device.setStatus(Status.New);
        device.setYear(year);
        deviceRepository.save(device);
    }

}

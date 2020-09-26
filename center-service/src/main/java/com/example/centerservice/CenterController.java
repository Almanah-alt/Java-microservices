package com.example.centerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/center")
public class CenterController {

    @Autowired
    private CenterRepository centerRepository;

    @GetMapping("")
    public List<Center> repairCenterList(){
        return centerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Center repairCenterList(@PathVariable Long id){
        return centerRepository.findById(id).get();
    }



    @PostMapping("")
    public void newCenter(@RequestBody Center repairCenter){
        if(repairCenter.getLocation() == null || repairCenter.getName() == null){
            throw new RuntimeException("all fields in repair center should be filled (location|name)");
        }
        centerRepository.save(repairCenter);
    }

    @PatchMapping("/{id}/location")
    public void updateCenterLocation (@PathVariable Long id,
                                     @RequestParam String location){
        if (id == null || location.equals("")){
            throw new RuntimeException("id or location should not be empty");
        }
        Center repairCenter = centerRepository.findById(id).get();
        repairCenter.setLocation(location);
        centerRepository.save(repairCenter);
    }

    @PatchMapping("/{id}/name")
    public void updateCenterName(@PathVariable Long id,
                                     @RequestParam String name){
        if (id == null || name.equals("")){
            throw new RuntimeException("id or location should not be empty");
        }
        Center repairCenter = centerRepository.findById(id).get();
        repairCenter.setName(name);
        centerRepository.save(repairCenter);
    }

}

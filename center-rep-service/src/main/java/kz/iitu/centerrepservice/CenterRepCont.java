package kz.iitu.centerrepservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/centerRep")
public class CenterRepCont {

    @Autowired
    private RepairerService repairerService;

    @Autowired
    private CenterService centerService;

    @Autowired
    private CenterRepRepository centerRepRepository;

    @GetMapping("")
    public List<CenterRep> centerReps(){
        return centerRepRepository.findAll();
    }

    @PostMapping("/{centerId}/{repId}")
    public void addRepToCenter(@PathVariable Long centerId, @PathVariable Long repId){
        Center center = centerService.getCenter(centerId);
        Repairer repairer = repairerService.getRepairer(repId);
        CenterRep centerRep = new CenterRep();

        centerRep.setCenterLocation(center.getLocation());
        centerRep.setCenterName(center.getName());
        centerRep.setIdOfRoom(repairer.getIdOfRoom());
        centerRep.setPhone(repairer.getPhone());
        centerRep.setPrice(repairer.getPrice());
        centerRep.setRepairerName(repairer.getName());
        centerRep.setUsername(repairer.getUsername());
        centerRep.setRepId(repId);
        centerRepRepository.save(centerRep);

    }

    @PatchMapping("/{id}")
    public void removeRepFromCenter(@PathVariable Long id){
        CenterRep centerRep = centerRepRepository.findByRepId(id);
        centerRepRepository.delete(centerRep);
        centerRepRepository.save(centerRep);


    }


}
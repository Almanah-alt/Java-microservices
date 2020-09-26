package kz.iitu.repairermicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairer")
public class RepairerController {

    @Autowired
    private RepairerRepository repairerRepository;

    @GetMapping("")
    public List<Repairer> repList(){
        return repairerRepository.findAll();
    }

    @GetMapping("/byPhone")
    public Repairer repByPhone(@RequestParam String phone){
        return repairerRepository.findByPhone(phone);
    }

    @GetMapping("/byId")
    public Repairer repById(@RequestParam Long id){
        return repairerRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteRep(@PathVariable Long id) {
        if (id == null){
            throw new NullPointerException("id must not be null");
        }
        repairerRepository.delete(repairerRepository.findById(id).get());
    }

    @GetMapping("/username/{name}")
    public Repairer repByName(@PathVariable String username){
        return repairerRepository.findByUsername(username);
    }

    @PostMapping("")
    public void newRepairer(@RequestBody Repairer repairer){
        repairerRepository.save(repairer);
    }
}

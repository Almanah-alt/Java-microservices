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

    @GetMapping("/byId/{id}")
    public Repairer repById(@PathVariable Long id){
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
    public Repairer repByName(@PathVariable String name){
        return repairerRepository.findByUsername(name);
    }

    @PostMapping("/{username}/{password}/{name}/{phone}/{idOfRoom}/{price}")
    public void newRepairer(@PathVariable String username, @PathVariable String password, @PathVariable String name, @PathVariable String phone, @PathVariable int idOfRoom, @PathVariable int price){
        Repairer repairer = new Repairer();
        repairer.setIdOfRoom(idOfRoom);
        repairer.setName(name);
        repairer.setPassword(password);
        repairer.setPhone(phone);
        repairer.setPrice(price);
        repairer.setUsername(username);
        repairerRepository.saveAndFlush(repairer);
    }
}

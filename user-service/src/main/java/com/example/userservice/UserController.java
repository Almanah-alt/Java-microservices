package com.example.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> repList(){
        return userRepository.findAll();
    }

    @GetMapping("/byPhone")
    public User repByPhone(@RequestParam String phone){
        return userRepository.findByPhone(phone);
    }

    @GetMapping("/byId/{id}")
    public User repById(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        }else throw new RuntimeException("User with " + username + "not found");
    }

    @DeleteMapping("/{id}")
    public void deleteRep(@PathVariable Long id) {
        if (id == null){
            throw new NullPointerException("id must not be null");
        }
        userRepository.delete(userRepository.findById(id).get());
    }
//
//    @GetMapping("/username/{name}")
//    public User repByName(@PathVariable String name){
//        return userDetailsService.loadUserByUsername(name);
//    }

//    @PostMapping("/{username}/{password}/{name}/{phone}/{idOfRoom}/{price}/{role}")
    @PostMapping("/signUp")
//    public void newUser(@PathVariable String username, @PathVariable String password, @PathVariable String name, @PathVariable String phone, @PathVariable int idOfRoom, @PathVariable int price, @PathVariable String role){
    public void newUser(@RequestBody User newUser){
        User user = userRepository.findByUsername(newUser.getUsername());
        if (user != null){
            throw new RuntimeException("With this " + user.getUsername() + " is exist");
        }
        if (newUser.getUsername().isEmpty() || newUser.getUsername().isEmpty()){
            throw new RuntimeException("username and password should not be empty");
        }
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        userRepository.saveAndFlush(newUser);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


package com.example.todorestwithauthorization.controllers;


import com.example.todorestwithauthorization.models.User;
import com.example.todorestwithauthorization.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id")long id){

        return userService.findById(id);
    }
    @PostMapping("/add")
    public String createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setRole("ROLE_USER");
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(newUser);
        return "User created";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "User with id: " + id + " deleted";
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = userService.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(updatedUser);
        return "User updated";
    }

}

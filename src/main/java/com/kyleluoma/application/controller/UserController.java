package com.kyleluoma.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyleluoma.application.model.User;
import com.kyleluoma.application.repository.UserRepository;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String userName,
                                            @RequestParam String hashedPassword,
                                            @RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam String email) {
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setHashedPassword(hashedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        userRepository.save(newUser);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

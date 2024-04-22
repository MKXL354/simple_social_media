package com.simple_social_media.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;
import com.simple_social_media.server.models.User;
import com.simple_social_media.server.repositories.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String passWord) {
        List<User> user = userRepo.findByUserName(userName);
        if (user.size() == 0) {
            return "not found";
        }
        if (!user.get(0).getPassWord().equals(passWord)) {
            return "wrong pass";
        }
        return "success";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody User user) {
        try {
            userRepo.save(user);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "duplicate username";
        }
    }
}

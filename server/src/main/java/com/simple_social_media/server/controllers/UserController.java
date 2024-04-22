package com.simple_social_media.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simple_social_media.server.models.Post;
import com.simple_social_media.server.models.User;
import com.simple_social_media.server.repositories.PostRepo;
import com.simple_social_media.server.repositories.UserRepo;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @PostMapping("/{userName}/post")
    public String post(@PathVariable("userName") String userName, @RequestBody Post post) {
        postRepo.save(post);
        return "success";
    }

    @GetMapping("/{userName}/posts")
    public List<Post> getPost(@PathVariable("userName") String userName) {
        User user = userRepo.findByUserName(userName).get(0);
        return postRepo.find(user.getId());
    }
}
// post, like, delete post, get posts, delete like, follow, delete follow
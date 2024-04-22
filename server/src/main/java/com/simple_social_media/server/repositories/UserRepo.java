package com.simple_social_media.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_social_media.server.models.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
    public List<User> findByUserName(String userName);
}

package com.simple_social_media.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simple_social_media.server.models.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends JpaRepository<User, Integer>{
    
}

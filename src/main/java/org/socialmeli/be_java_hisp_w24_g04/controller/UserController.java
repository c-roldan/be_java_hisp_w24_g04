package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private IUserService userRepository;

    @Autowired
    public UserController(IUserService userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> userFollowedList(@PathVariable int userId){

    }
}

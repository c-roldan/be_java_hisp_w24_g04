package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userRepository;

    @Autowired
    public UserController(IUserService userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable String userId, @PathVariable String userIdToFollow) {
        try {
            Integer userIdInt = Integer.parseInt(userId);
            Integer userIdToFollowInt = Integer.parseInt(userIdToFollow);
            userRepository.follow(userIdInt, userIdToFollowInt);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            throw new BadRequestException("User id and user id to follow must be integers");
        }
    }
}

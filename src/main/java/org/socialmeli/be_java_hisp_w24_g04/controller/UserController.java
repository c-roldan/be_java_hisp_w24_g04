package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowerCountDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserFollowedDTO> userFollowedList(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserFollowedDTO(userService.findById(userId)));
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<UserFollowerCountDTO> getFollowersCount(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(new UserFollowerCountDTO(user.getUserId(), user.getUsername(), user.getFollowers().size()));
    }
}

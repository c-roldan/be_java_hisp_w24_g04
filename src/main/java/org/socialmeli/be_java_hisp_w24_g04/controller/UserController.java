package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.dto.*;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SingleResponseDTO> getFollowers(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        User user = userService.findById(userId);
        Set<UserDTO> followers = userService.getFollowers(userId);
        UserFollowersDTO dto = new UserFollowersDTO(user.getUserId(), user.getUsername(), followers);
        return ResponseEntity.ok(new SingleResponseDTO(200, (order == null) ? dto : dto.orderBy(order)));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<SingleResponseDTO> userFollowedList(@PathVariable int userId, @RequestParam(required = false) String order) {
        User user = userService.findById(userId);
        Set<UserDTO> followed = userService.getFollowed(userId);
        UserFollowedDTO dto = new UserFollowedDTO(user.getUserId(), user.getUsername(), followed);
        return ResponseEntity.ok(new SingleResponseDTO(200, (order == null) ? user : dto.orderBy(order)));
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SingleResponseDTO> getFollowersCount(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        Integer followersCount = userService.getFollowersCount(userId);
        UserFollowerCountDTO response = new UserFollowerCountDTO(user.getUserId(), user.getUsername(), followersCount);
        return ResponseEntity.ok(new SingleResponseDTO(200, response));
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable String userId, @PathVariable String userIdToFollow) {
        try {
            Integer userIdInt = Integer.parseInt(userId);
            Integer userIdToFollowInt = Integer.parseInt(userIdToFollow);
            userService.follow(userIdInt, userIdToFollowInt);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            throw new BadRequestException("User id and user id to follow must be integers");
        }
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollow(@PathVariable String userId, @PathVariable String userIdToUnfollow) {
        try {
            Integer userIdInt = Integer.parseInt(userId);
            Integer userIdToUnfollowInt = Integer.parseInt(userIdToUnfollow);
            userService.unfollow(userIdInt, userIdToUnfollowInt);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            throw new BadRequestException("User id and user id to unfollow must be integers");
        }
    }
}

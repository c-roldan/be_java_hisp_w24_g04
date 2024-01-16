package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowerCountDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.dto.SingleResponseDTO;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SingleResponseDTO> getFollowers(@PathVariable Integer userId) {
        return ResponseEntity.ok(new SingleResponseDTO(200, userService.getFollowers(userId)));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowedDTO> userFollowedList(@PathVariable int userId, @RequestParam(required = false) String order) {
        UserFollowedDTO user = userService.getUserFollowedDTO(userService.findById(userId));
        return ResponseEntity.ok((order == null) ? user : user.orderBy(order));
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserFollowerCountDTO> getFollowersCount(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(new UserFollowerCountDTO(user.getUserId(), user.getUsername(), user.getFollowers().size()));
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
}

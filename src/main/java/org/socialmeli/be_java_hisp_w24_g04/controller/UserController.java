package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.NotFoundException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private IUserService userRepository;

    @Autowired
    public UserController(IUserService userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserFollowedDTO> userFollowedList(@PathVariable int userId){
        Optional<User> user= userRepository.findById(userId);
        if(user.isEmpty())
            throw new NotFoundException("El usuario no existe");
        return ResponseEntity.ok(userRepository.getUserFollowedDTO(user.get()));
    }
}

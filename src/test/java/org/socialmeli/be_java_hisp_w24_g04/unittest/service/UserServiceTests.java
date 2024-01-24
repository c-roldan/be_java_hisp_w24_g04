package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.UserService;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void getFollowersCountTest() {
        // arrange
        Set<UserDTO> followers = Set.of(
                new UserDTO(2, "user2"),
                new UserDTO(3, "user3"),
                new UserDTO(4, "user4")
        );

        User user = new User();
        user.setUserId(1);
        user.setFollowers(followers);

        Mockito.when(userRepository.get(user.getUserId())).thenReturn(Optional.of(user));

        // act
        Integer followersCount = userService.findById(user.getUserId()).getFollowers().size();

        // assert
        Assertions.assertEquals(3, followersCount);
    }
}

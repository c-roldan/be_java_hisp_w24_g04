package org.socialmeli.be_java_hisp_w24_g04.unittest.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.NotFoundException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserRepositoryTests {

    IUserRepository userRepository = new UserRepository();

    private User createUser(int id, String username) {
        return new User(id, username, new HashSet<>(), new HashSet<>());
    }
    @Test
    public void unfollowValidUserId(){
        // Arrange
        User user1 = createUser(1, "user1");
        User user2 = createUser(2, "user2");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.follow(1, 2);

        // Act
        userRepository.unfollow(1, 2);

        // Assert
        Assertions.assertFalse(user1.getFollowed().contains(user2));
        Assertions.assertFalse(user2.getFollowers().contains(user1));
    }

    @Test
    public void unfollowInvalidUserId(){
        // Arrange
        User user = createUser(1, "user1");
        userRepository.save(user);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userRepository.unfollow(1, 2));
    }
}

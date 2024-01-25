package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.socialmeli.be_java_hisp_w24_g04.model.Product;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.PostRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.PostService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Mock
    UserRepository userRepository;

    private User userWithId101;

    @BeforeEach
    public void setUp() {
        UserDTO followedUserDTO = new UserDTO(102, "User2");
        userWithId101 = new User(101, "User1", Set.of(followedUserDTO), Set.of());
    }

    @Test
    @DisplayName("Test order by date asc")
    public void orderByDateAscTest() {

        String ascParam = "date_asc";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, ascParam), "Param date_asc should not throw exception");

    }

    @Test
    @DisplayName("Test order by date desc")
    public void orderByDateDescTest() {

        String descParam = "date_desc";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, descParam), "Param date_desc should not throw exception");

    }

    @Test
    @DisplayName("Test order by date empty param")
    public void orderByDateEmptyParamTest() {

        String emptyParam = null;

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, emptyParam), "Empty param should not throw exception");

    }

    @Test
    @DisplayName("Test order by date with unknown param")
    public void orderByDateWithUnknownParamTest() {

        String unknownParam = "any_param";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertThrows(
                BadRequestException.class,
                () -> postService.searchAllFollowedLastTwoWeeks(101, unknownParam), "Unknown param should throw exception");

    }

}

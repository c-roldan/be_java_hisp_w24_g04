package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
        Product product1 = new Product(1, "Product1", "Type1", "Brand1", "Color1", "Notes1");
        Post post1 = new Post(1, 101, LocalDate.of(2024, 1, 10), product1, 1, 29.99);
        Product product2 = new Product(2, "Product2", "Type2", "Brand2", "Color2", "Notes2");
        Post post2 = new Post(2, 101, LocalDate.of(2024, 1, 10), product2, 2, 39.99);
        UserDTO followedUserDTO = new UserDTO(102, "User2");
        userWithId101 = new User(101, "User1", Set.of(followedUserDTO), Set.of(), Arrays.asList(post1, post2));
    }

    @Test
    public void orderByDateAscTest() {

        String ascParam = "date_asc";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, ascParam));

    }

    @Test
    public void orderByDateDescTest() {

        String descParam = "date_desc";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, descParam));

    }

    @Test
    public void orderByDateEmptyParamTest() {

        String emptyParam = null;

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertDoesNotThrow(() -> postService.searchAllFollowedLastTwoWeeks(101, emptyParam));

    }

    @Test
    public void orderByDateWithUnknownParamTest() {

        String unknownParam = "any_param";

        Mockito.when(userRepository.get(101)).thenReturn(Optional.of(userWithId101));

        Assertions.assertThrows(
                BadRequestException.class,
                () -> postService.searchAllFollowedLastTwoWeeks(101, unknownParam));

    }

}

package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.dto.PostDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.socialmeli.be_java_hisp_w24_g04.model.Product;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.PostRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.PostService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Mock
    UserRepository userRepository;

    private User userWithId103;
    private List<PostDTO> expectedPostFor103;

    @BeforeEach
    public void setUp() {
        expectedPostFor103 = List.of(
                new PostDTO(103,
                        4,
                        "2024-01-13",
                        new Product(4,
                                "Product 4",
                                "Electronics",
                                "ExampleBrand4",
                                "Black",
                                "Product notes 4"),
                        2,
                        39.99),
                new PostDTO(103,
                        5,
                        "2024-01-14",
                        new Product(5,
                                "Product 5",
                                "Clothing",
                                "ExampleBrand5",
                                "White",
                                "Product notes 5"),
                        1,
                        59.99),
                new PostDTO(103,
                        6,
                        "2024-01-15",
                        new Product(6,
                                "Product 6",
                                "Home",
                                "ExampleBrand6",
                                "Yellow",
                                "Product notes 6"),
                        1,
                        59.99)
        );

        userWithId103 = new User(103, "User 103", "
    }

    @Test
    public void searchAllFollowedLastTwoWeeksTest() {

        when(userRepository.get(103)).thenReturn(userWithId101);
        List<PostDTO> currentPosts = postService.searchAllFollowedLastTwoWeeks(103, "date_asc");
        Assertions.assertEquals(expectedPosts, currentPosts);



    }
}

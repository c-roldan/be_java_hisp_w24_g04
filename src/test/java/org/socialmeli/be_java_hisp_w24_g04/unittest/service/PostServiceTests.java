package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.repository.PostRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.PostService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;
}

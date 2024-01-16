package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserPostDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.socialmeli.be_java_hisp_w24_g04.repository.IPostRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.IProductRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;

    @Autowired
    public PostService(
            IPostRepository postRepository,
            IUserRepository userRepository,
            IProductRepository productRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public UserPostDTO createUserPost(UserPostDTO userPost) {
        var idFound = userRepository
                .findAll()
                .stream()
                .filter(user -> user.getUserId().equals(userPost.user_id()))
                .findFirst()
                .orElse(null);

        if (idFound == null)
            return null;

        var posts = postRepository.findAll();
        var postId = 0;

        if (posts != null)
            postId = posts.size() + 1;

        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        final LocalDate dt = LocalDate.parse(userPost.date(), dateFormat);

        productRepository.save(userPost.product());
        postRepository.save(new Post(
                postId,
                userPost.user_id(),
                dt,
                userPost.product(),
                userPost.category(),
                userPost.price()
        ));

        return userPost;
    }

    private void getAllPostAndProducts() {
        System.out.printf("Posts: %s\n", postRepository.findAll().toString());
        System.out.printf("Products: %s\n", productRepository.findAll().toString());
    }
}

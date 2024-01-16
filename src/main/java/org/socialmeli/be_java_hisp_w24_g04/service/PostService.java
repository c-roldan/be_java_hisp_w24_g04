package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.PostDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.NotFoundException;
import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.socialmeli.be_java_hisp_w24_g04.repository.IPostRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final IPostRepository postRepository;
    private final IUserRepository userRepository;

    public PostService(IPostRepository postRepository, IUserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDTO> searchAllFollowedLastTwoWeeks(Integer userId) {

        List<PostDTO> foundPosts = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();
        try{
            userRepository.get(userId).get().getFollowed().forEach(followed -> {
            postRepository.findAll().stream().filter(post -> post.getUserId().equals(followed.user_id()) && (ChronoUnit.DAYS.between(post.getDate(),dateNow) <= 14)).forEach(post -> {
                    PostDTO postDTO = new PostDTO(
                        post.getUserId(),
                        post.getPostId(),
                        post.getDate().toString(),
                        post.getProduct(),
                        post.getCategory(),
                        post.getPrice()
                    );
                    foundPosts.add(postDTO);
                });
            });
            return foundPosts;
        } catch (Exception e) {
            throw new NotFoundException("El usuario no existe");
        }
    }
}

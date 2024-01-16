package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.User;

import java.util.Optional;

public interface IUserService {
    User findById(int id);
    UserFollowedDTO getUserFollowedDTO(User user);
    void follow(Integer userId, Integer userIdToFollow);
    void unfollow(Integer userId, Integer userIdToUnfollow);
}

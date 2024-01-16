package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowersDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.User;

public interface IUserService {
    UserFollowersDTO getFollowers(Integer userId);
    User findById(int id);
    UserFollowedDTO getUserFollowedDTO(User user);
    void follow(Integer userId, Integer userIdToFollow);
}

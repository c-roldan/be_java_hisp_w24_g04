package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowersDTO;

public interface IUserService {
    UserFollowersDTO getFollowers(Integer userId);
}

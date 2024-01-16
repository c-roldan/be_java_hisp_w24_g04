package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.model.User;

import java.util.Optional;

public interface IUserService {
    public Optional<User> findById(int id);
    public UserFollowedDTO getUserFollowedDTO(User user);
}
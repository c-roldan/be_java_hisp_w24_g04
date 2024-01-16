package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTOFollowers;

import java.util.List;

public interface IUserService {
    UserDTOFollowers getFollowers(Integer userId);
}

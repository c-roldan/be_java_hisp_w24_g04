package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        userRepository.follow(userId, userIdToFollow);
    }
}

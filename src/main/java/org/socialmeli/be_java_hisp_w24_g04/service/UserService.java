package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowersDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.NotFoundException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.socialmeli.be_java_hisp_w24_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserFollowersDTO getFollowers(Integer userId) {

        Optional<User> foundUser= userRepository.get(userId);

        if(foundUser.isEmpty()) throw new NotFoundException("No se encontró el usuario con id " + userId);

        return new UserFollowersDTO(
                foundUser.get().getUserId(),
                foundUser.get().getUsername(),
                foundUser.get().getFollowers());
    }
}

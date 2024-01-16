package org.socialmeli.be_java_hisp_w24_g04.repository;

import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.NotFoundException;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private List<User> userRepository;

    public UserRepository() {
        this.userRepository = loadProducts();
    }

    private ArrayList<User> loadProducts() {
        return new ArrayList<>();
    }

    @Override
    public User save(User entity) {
        if (entity == null)
            return null;

        userRepository.add(entity);

        return entity;
    }

    @Override
    public User remove(Integer id) {
        var productToDelete = userRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        userRepository.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<User> get(Integer id) {
        return userRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return userRepository;
    }

    @Override
    public User update(User entity) {
        userRepository = userRepository
                .stream()
                .map(user -> {
                    if (user.getUserId().equals(entity.getUserId()))
                        return entity;
                    else
                        return user;
                }).toList();

        return entity;
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        var user = userRepository
                .stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        var userToFollow = userRepository
                .stream()
                .filter(u -> u.getUserId().equals(userIdToFollow))
                .findFirst()
                .orElse(null);

        if (user == null || userToFollow == null)
            throw new NotFoundException("User not found");

        user.getFollowed().add(new UserDTO(userToFollow.getUserId(), userToFollow.getUsername()));
        userToFollow.getFollowers().add(new UserDTO(user.getUserId(), user.getUsername()));
    }
}

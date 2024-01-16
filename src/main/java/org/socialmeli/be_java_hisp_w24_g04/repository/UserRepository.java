package org.socialmeli.be_java_hisp_w24_g04.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.socialmeli.be_java_hisp_w24_g04.model.Product;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{
    private List<User> usersRepository;

    public UserRepository(List<User> usersRepository) {
        this.usersRepository = loadUsers();
    }

    private ArrayList<User> loadUsers() {
        ArrayList<User> data = null;
        File file;
        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<ArrayList<User>> typeRef = new TypeReference<>() {};
        try {
            file = ResourceUtils.getFile("classpath:data/users.json");
            data = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public User save(User entity) {
        if (entity == null)
            return null;

        usersRepository.add(entity);

        return entity;
    }

    @Override
    public User remove(Integer id) {
        var productToDelete = usersRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        usersRepository.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<User> get(Integer id) {
        return usersRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return usersRepository;
    }

    @Override
    public User update(User entity) {
        usersRepository = usersRepository
                .stream()
                .map(user -> {
                    if (user.getUserId().equals(entity.getUserId()))
                        return entity;
                    else
                        return user;
                }).toList();

        return entity;
    }
}

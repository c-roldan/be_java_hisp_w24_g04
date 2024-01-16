package org.socialmeli.be_java_hisp_w24_g04.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private List<User> users;

    public UserRepository() {
        this.users = loadProducts();
    }

    private ArrayList<User> loadProducts() {
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

        users.add(entity);

        return entity;
    }

    @Override
    public User remove(Integer id) {
        var productToDelete = users
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        users.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<User> get(Integer id) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User update(User entity) {
        users = users
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

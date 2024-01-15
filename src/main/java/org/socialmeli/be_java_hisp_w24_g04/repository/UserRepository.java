package org.socialmeli.be_java_hisp_w24_g04.repository;

import org.socialmeli.be_java_hisp_w24_g04.model.Product;
import org.socialmeli.be_java_hisp_w24_g04.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{
    private List<User> productRepository;

    public void setProductRepository() {
        this.productRepository = loadProducts();
    }

    private ArrayList<User> loadProducts() {
        return new ArrayList<>();
    }

    @Override
    public User save(User entity) {
        if (entity == null)
            return null;

        productRepository.add(entity);

        return entity;
    }

    @Override
    public User remove(Integer id) {
        var productToDelete = productRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        productRepository.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<User> get(Integer id) {
        return productRepository
                .stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return productRepository;
    }

    @Override
    public User update(User entity) {
        productRepository = productRepository
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

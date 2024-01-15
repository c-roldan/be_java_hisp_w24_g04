package org.socialmeli.be_java_hisp_w24_g04.repository;

import org.socialmeli.be_java_hisp_w24_g04.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {
    private List<Product> productRepository;

    public void setProductRepository() {
        this.productRepository = loadProducts();
    }

    private ArrayList<Product> loadProducts() {
        return new ArrayList<>();
    }

    @Override
    public Product save(Product entity) {
        if (entity == null)
            return null;

        productRepository.add(entity);

        return entity;
    }

    @Override
    public Product remove(Integer id) {
        var productToDelete = productRepository
                .stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        productRepository.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productRepository
                .stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> findAll() {
        return productRepository;
    }

    @Override
    public Product update(Product entity) {
        productRepository = productRepository
                .stream()
                .map(product -> {
                    if (product.getProductId().equals(entity.getProductId()))
                        return entity;
                    else
                        return product;
                }).toList();

        return entity;
    }
}

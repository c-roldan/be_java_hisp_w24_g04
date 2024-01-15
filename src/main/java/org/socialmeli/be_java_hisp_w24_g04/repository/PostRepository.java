package org.socialmeli.be_java_hisp_w24_g04.repository;

import org.socialmeli.be_java_hisp_w24_g04.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository implements IPostRepository{
    private List<Post> postRepository;

    public void setProductRepository() {
        this.postRepository = loadProducts();
    }

    private ArrayList<Post> loadProducts() {
        return new ArrayList<>();
    }

    @Override
    public Post save(Post entity) {
        if (entity == null)
            return null;

        postRepository.add(entity);

        return entity;
    }

    @Override
    public Post remove(Integer id) {
        var productToDelete = postRepository
                .stream()
                .filter(post -> post.getPostId().equals(id))
                .findFirst()
                .orElse(null);

        if (productToDelete == null)
            return null;

        postRepository.remove(productToDelete);

        return productToDelete;
    }

    @Override
    public Optional<Post> get(Integer id) {
        return postRepository
                .stream()
                .filter(post -> post.getPostId().equals(id))
                .findFirst();
    }

    @Override
    public List<Post> findAll() {
        return postRepository;
    }

    @Override
    public Post update(Post entity) {
        postRepository = postRepository
                .stream()
                .map(post -> {
                    if (post.getPostId().equals(entity.getPostId()))
                        return entity;
                    else
                        return post;
                }).toList();

        return entity;
    }
}

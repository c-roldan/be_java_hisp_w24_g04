package org.socialmeli.be_java_hisp_w24_g04.controller;

import org.socialmeli.be_java_hisp_w24_g04.dto.PostDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.SingleResponseDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserPostDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Comparator;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IPostService postService;

    @Autowired
    public ProductController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> searchAllFollowedLastTwoWeeks(@PathVariable Integer userId, @RequestParam(required = false) String order){
        var response = postService.searchAllFollowedLastTwoWeeks(userId);
        if(order == null){
            return new ResponseEntity<>(postService.searchAllFollowedLastTwoWeeks(userId), HttpStatus.OK);
        } else {
            if(order.equals("date_asc")) {
                response.sort(Comparator.comparing(PostDTO::date));
            } else if (order.equals("date_desc")) {
                response.sort(Comparator.comparing(PostDTO::date).reversed());
            } else {
                throw new BadRequestException("Order must be date_asc or date_desc");
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

    @PostMapping("/post")
    public ResponseEntity<SingleResponseDTO> createUserPost(@RequestBody UserPostDTO userPost) {
        var response = postService.createUserPost(userPost);

        if (response == null)
            throw new BadRequestException("Couldn't create user's post. Please, try again with a valid" +
                    " user ID.");

        return ResponseEntity.ok(new SingleResponseDTO(200, response));
    }
}

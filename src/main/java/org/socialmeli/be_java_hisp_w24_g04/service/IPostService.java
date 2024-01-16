package org.socialmeli.be_java_hisp_w24_g04.service;

import org.socialmeli.be_java_hisp_w24_g04.dto.PostDTO;

import java.util.List;

public interface IPostService {
    List<PostDTO> searchAllFollowedLastTwoWeeks(Integer userId);
}

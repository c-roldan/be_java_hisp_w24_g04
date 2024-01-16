package org.socialmeli.be_java_hisp_w24_g04.dto;

import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;


import java.util.Set;
import java.util.stream.Collectors;

public record UserFollowersDTO(Integer user_id, String user_name, Set<UserDTO> followers){
}

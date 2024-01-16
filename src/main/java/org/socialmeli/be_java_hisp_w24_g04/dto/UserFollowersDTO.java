package org.socialmeli.be_java_hisp_w24_g04.dto;

import java.util.List;
import java.util.Set;

public record UserFollowersDTO(Integer user_id, String user_name, Set<UserDTO> followers){
}

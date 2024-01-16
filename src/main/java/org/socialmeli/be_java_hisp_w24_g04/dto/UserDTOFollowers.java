package org.socialmeli.be_java_hisp_w24_g04.dto;

import java.util.List;

public record UserDTOFollowers(Integer user_id, String user_name, List<UserDTO> followers){
}

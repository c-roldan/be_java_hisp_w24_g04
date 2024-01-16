package org.socialmeli.be_java_hisp_w24_g04.dto;

import java.util.ArrayList;

public record UserFollowedDTO(Integer user_id, String user_name, ArrayList<UserDTO> followed) {
}

package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowedDTO;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserFollowersDTO;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.socialmeli.be_java_hisp_w24_g04.service.UserService;
import java.util.Set;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    UserDTO userDTO1 = new UserDTO(1,"user1");
    UserDTO userDTO2 = new UserDTO(2,"user2");
    UserDTO userDTO3 = new UserDTO(3,"user3");
    Set<UserDTO> list = List.of(userDTO1,userDTO3, userDTO2).stream().collect(Collectors.toSet());

    @Test
    @DisplayName("Order by name asc test")
    public void orderByNameAscTest() {
        Assertions.assertDoesNotThrow(() -> IUserService.orderList("name_asc", list), "Order by name asc should not throw an exception");
    }

    @Test
    @DisplayName("Order by name desc test")
    public void orderByNameDescTest() {
        Assertions.assertDoesNotThrow(() -> IUserService.orderList("name_desc", list), "Order by name desc should not throw an exception");
    }

    @Test
    @DisplayName("Order by name invalid param test")
    public void orderByNameInvalidParamTest() {
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("nameAsc", list), "Order by this param should throw an exception");
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("id_asc", list), "Order by this param should throw an exception");
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("name_a", list), "Order by this param should throw an exception");
    }

    @Test
    public void testRightOrderLists() {
        Set<UserDTO> ascList = List.of(userDTO1,userDTO2,userDTO3).stream().collect(Collectors.toSet());
        Set<UserDTO> descList = List.of(userDTO3,userDTO2,userDTO1).stream().collect(Collectors.toSet());

        Assertions.assertEquals(ascList, IUserService.orderList("name_asc", list));
        Assertions.assertEquals(descList, IUserService.orderList("name_desc", list));
    }

    @Test
    public void testWrongOrderLists() throws BadRequestException {
        Set<UserDTO> list = List.of(new UserDTO(1,"user1")).stream().collect(Collectors.toSet());

        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("name_asc_algo", list));
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("id_asc", list));
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("name_ascendent", list));
    }

    @Test
    public void testUserFollowedDTO() {
        Set<UserDTO> ascList = List.of(userDTO1,userDTO2,userDTO3).stream().collect(Collectors.toSet());
        Set<UserDTO> descList = List.of(userDTO3,userDTO2,userDTO1).stream().collect(Collectors.toSet());
        UserFollowedDTO dto = new UserFollowedDTO(4, "user 4", list);
        UserFollowedDTO expectedAsc = new UserFollowedDTO(4, "user 4", ascList);
        UserFollowedDTO expectedDesc = new UserFollowedDTO(4, "user 4", descList);

        Assertions.assertEquals(expectedAsc, dto.orderBy("name_asc"));
        Assertions.assertEquals(expectedDesc, dto.orderBy("name_asc"));
    }

    @Test
    public void testUserFollowersDTO() {
        Set<UserDTO> ascList = List.of(userDTO1,userDTO2,userDTO3).stream().collect(Collectors.toSet());
        Set<UserDTO> descList = List.of(userDTO3,userDTO2,userDTO1).stream().collect(Collectors.toSet());
        UserFollowersDTO dto = new UserFollowersDTO(4, "user 4", list);
        UserFollowersDTO expectedAsc = new UserFollowersDTO(4, "user 4", ascList);
        UserFollowersDTO expectedDesc = new UserFollowersDTO(4, "user 4", descList);

        Assertions.assertEquals(expectedAsc, dto.order("name_asc"));
        Assertions.assertEquals(expectedDesc, dto.order("name_desc"));
    }
}

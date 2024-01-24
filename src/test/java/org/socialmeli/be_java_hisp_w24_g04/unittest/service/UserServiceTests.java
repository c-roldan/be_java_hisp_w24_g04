package org.socialmeli.be_java_hisp_w24_g04.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.socialmeli.be_java_hisp_w24_g04.dto.UserDTO;
import org.socialmeli.be_java_hisp_w24_g04.exception.BadRequestException;
import org.socialmeli.be_java_hisp_w24_g04.repository.UserRepository;
import org.socialmeli.be_java_hisp_w24_g04.service.IUserService;
import org.socialmeli.be_java_hisp_w24_g04.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    private Set<UserDTO> users;

    @BeforeEach
    public void setUp() {
        users = new HashSet<>(Arrays.asList(
                new UserDTO(101, "User1"),
                new UserDTO(103, "User3"),
                new UserDTO(102, "User2")
        ));
    }

    @Test
    public void orderByNameHappyPathTest() {
        Assertions.assertDoesNotThrow(() -> IUserService.orderList("name_asc", users), "Order by name asc should not throw an exception");
        Assertions.assertDoesNotThrow(() -> IUserService.orderList("name_desc", users), "Order by name desc should not throw an exception");
    }

    @Test
    public void orderByNameSadPathTest() {
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("nameAsc", users), "Order by this param should throw an exception");
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("id_asc", users), "Order by this param should throw an exception");
        Assertions.assertThrows(BadRequestException.class, () -> IUserService.orderList("name_a", users), "Order by this param should throw an exception");
    }

}

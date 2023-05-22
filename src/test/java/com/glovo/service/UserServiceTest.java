package com.glovo.service;

import com.glovo.DummyObjects;
import com.glovo.converter.UserConverter;
import com.glovo.entity.Role;
import com.glovo.entity.User;
import com.glovo.model.UserDTO;
import com.glovo.repository.RoleRepository;
import com.glovo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    private UserService userService;
    private User user;
    private Role role;

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        userService = new UserService(passwordEncoder, roleRepository, userRepository, confirmationTokenService, emailService);
        user = DummyObjects.getUser();
        role = DummyObjects.getRole();
    }

    @Test
    void findUserByUsername() {
        // given
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        // when
        User actualUser = userService.findUserByUsername(user.getUsername());

        // then
        Assertions.assertEquals(user, actualUser);

    }

    @Test
    void findAllUsers() {
        // given
        Iterable<User> userIterable = List.of(user);
        List<UserDTO> userDTOSExpected = List.of(UserConverter.userToUserDTO(user));
        when(userRepository.findAll()).thenReturn(userIterable);

        // when
        List<UserDTO> userDTOSActual = userService.findAllUsers();

        // then
        Assertions.assertEquals(userDTOSExpected, userDTOSActual);
    }

    @Test
    void saveUser() {
        // given
        when(passwordEncoder.encode(anyString())).thenReturn(user.getPassword());
        when(roleRepository.findByRoleName(role.getRoleName())).thenReturn(role);

        // when
        userService.saveUser(UserConverter.userToUserDTO(user));

        // then
        Assertions.assertEquals(user, getCapturedUser());

    }

    private User getCapturedUser() {
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        return userArgumentCaptor.getValue();
    }
}
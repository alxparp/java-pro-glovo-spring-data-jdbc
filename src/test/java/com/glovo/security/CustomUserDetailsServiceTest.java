package com.glovo.security;

import com.glovo.DummyObjects;
import com.glovo.entity.Permission;
import com.glovo.entity.Role;
import com.glovo.entity.User;
import com.glovo.repository.PermissionRepository;
import com.glovo.repository.RoleRepository;
import com.glovo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PermissionRepository permissionRepository;
    private CustomUserDetailsService detailsService;
    private User user;
    private Role role;
    private Permission permission;

    @BeforeEach
    void setUp() {
        detailsService = new CustomUserDetailsService(userRepository, roleRepository, permissionRepository);
        user = DummyObjects.getUser();
        role = DummyObjects.getRole();
        permission = DummyObjects.getPermission();
    }

    @Test
    void loadUserByUsername() {
        // given
        UserDetails userDetailsExpected = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Set.of(
                        new SimpleGrantedAuthority(permission.getPermissionName()),
                        new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
                ))
                .build();
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(roleRepository.findById(anyInt())).thenReturn(Optional.of(role));
        when(permissionRepository.findById(anyInt())).thenReturn(Optional.of(permission));

        // when
        UserDetails userDetailsActual = detailsService.loadUserByUsername(user.getUsername());

        // then
        Assertions.assertEquals(userDetailsExpected, userDetailsActual);

        assertThrows(NoSuchElementException.class, () -> {
            detailsService.loadUserByUsername(null);
        });
    }
}
package com.glovo;

import com.glovo.entity.Permission;
import com.glovo.entity.Role;
import com.glovo.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;

public class DummyObjects {

    public static User getUser() {
        User user = User.builder()
                .firstName("Mykola")
                .lastName("Mykolenko")
                .email("mykola@gmail.com")
                .username("mykola")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles(new HashSet<>())
                .build();
        user.addRole(getRole());
        return user;
    }

    public static Role getRole() {
        Role role = Role.builder()
                .roleId(1)
                .roleName("ADMIN")
                .permissions(new HashSet<>())
                .build();
        role.addPermission(getPermission());
        return role;
    }

    public static Permission getPermission() {
        return Permission.builder()
                .permissionId(1)
                .permissionName("write")
                .build();
    }

}

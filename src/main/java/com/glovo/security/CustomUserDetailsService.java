package com.glovo.security;

import com.glovo.entity.Permission;
import com.glovo.entity.Role;
import com.glovo.entity.User;
import com.glovo.entity.ref.PermissionRef;
import com.glovo.entity.ref.RoleRef;
import com.glovo.repository.PermissionRepository;
import com.glovo.repository.RoleRepository;
import com.glovo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public CustomUserDetailsService(UserRepository userRepository,
                                    RoleRepository roleRepository,
                                    PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow();
        List<Role> roles = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        for (RoleRef roleRef : user.getRoles()) {
            Role role = roleRepository.findById(roleRef.getRoleId()).orElseThrow();
            roles.add(role);
            for (PermissionRef permissionRef : role.getPermissions()) {
                permissions.add(permissionRepository.findById(permissionRef.getPermissionId()).orElseThrow());
            }
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getGrantedAuthorities(permissions, roles))
                .build();
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(List<Permission> permissions, List<Role> roles) {
        Set<SimpleGrantedAuthority> grantedAuthorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toSet());
        for (Role role : roles)
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return grantedAuthorities;
    }

}

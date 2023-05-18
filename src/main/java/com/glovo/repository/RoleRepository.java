package com.glovo.repository;

import com.glovo.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRoleName(String roleName);

}

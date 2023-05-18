package com.glovo.repository;

import com.glovo.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}

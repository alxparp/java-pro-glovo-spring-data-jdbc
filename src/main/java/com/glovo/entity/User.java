package com.glovo.entity;

import com.glovo.entity.ref.ProductRef;
import com.glovo.entity.ref.RoleRef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Table("USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Persistable<String> {

    @Id
    @Column("USERNAME")
    private String username;

    @Column("PASSWORD")
    private String password;

    @Column("FIRST_NAME")
    private String firstName;

    @Column("LAST_NAME")
    private String lastName;

    @Column("EMAIL")
    private String email;

    @MappedCollection(idColumn = "USERNAME")
    private Set<RoleRef> roles;

    public void addRole(Role role) {
        roles.add(new RoleRef(role.getRoleId()));
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}

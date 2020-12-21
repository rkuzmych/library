package com.rkuzmych.library.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "usr", catalog = "library")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "usr_name")
    @NotBlank(message = "Username can't be empty")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password can't be empty")
    private String password;

    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

}

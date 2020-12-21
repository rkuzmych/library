package com.rkuzmych.library.impl;

import com.rkuzmych.library.domain.User;
import com.rkuzmych.library.domain.UserRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActive() {
        user.setActive(true);
    }

    public boolean isAdmin() {
        return user.getRoles().contains(UserRole.ADMIN);
    }

}

package com.rkuzmych.library.Service;

import com.rkuzmych.library.domain.User;
import com.rkuzmych.library.domain.UserRole;
import com.rkuzmych.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userServiceDetails")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
        User domainUser = userRepository.findByUsername(name);

        if (domainUser == null) {
            throw new UsernameNotFoundException(name);
        }

        Set<UserRole> roles = domainUser.getRoles();

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(UserRole role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.setUser(domainUser);

        return userDetailsImpl;
    }
}

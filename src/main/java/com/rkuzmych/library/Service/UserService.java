package com.rkuzmych.library.Service;

import com.rkuzmych.library.domain.User;
import com.rkuzmych.library.domain.UserRole;
import com.rkuzmych.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userService")
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(
            User user
    ) {
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActive(true);

        userRepository.save(user);
    }
}

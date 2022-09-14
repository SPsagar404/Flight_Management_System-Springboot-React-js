package com.axyya.service;

import java.util.Optional;

import com.axyya.model.Role;
import com.axyya.model.User;

/**
 * @author sa
 * @date 10.10.2021
 * @time 11:31
 */
public interface UserService
{
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);
}

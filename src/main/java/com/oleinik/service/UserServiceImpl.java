package com.oleinik.service;


import com.oleinik.constants.Roles;
import com.oleinik.entity.Role;
import com.oleinik.entity.User;
import com.oleinik.repository.RoleRepository;
import com.oleinik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role user_role = roleRepository.findByName(Roles.ROLE_USER);
        roles.add(user_role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

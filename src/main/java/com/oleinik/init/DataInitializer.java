package com.oleinik.init;


import com.oleinik.cache.IndexedIdCache;
import com.oleinik.constants.Roles;
import com.oleinik.entity.Role;
import com.oleinik.entity.User;
import com.oleinik.repository.RoleRepository;
import com.oleinik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IndexedIdCache idCache;

    @Override
    public void run(String... args) throws Exception {

        idCache.sync();

        //removeRolesAndUsers();

        if (userRepository.findByUsername("ADMIN") != null) return;

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Roles.ROLE_ADMIN));
        roles.add(new Role(Roles.ROLE_USER));

        User user = new User();
        user.setUsername("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("ADMIN_ADMIN"));
        user.setRoles(roles);
        userRepository.save(user);

    }

    private void removeRolesAndUsers() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}

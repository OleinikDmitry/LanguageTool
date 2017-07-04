package com.oleinik.service;


import com.oleinik.entity.User;

public interface UserService {
    void create(User user);

    void save(User user);

    User findByUsername(String username);
}

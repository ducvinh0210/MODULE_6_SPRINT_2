package com.codegym.service.security;

import com.codegym.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String existsByUserName(String username);

    Optional<User> findUserByUsername(String username);

    List<User> findAll();

    User findByUsername(String name);

    void saveCreateGmail(User user);

    Optional<User> showUsername(String username);
}

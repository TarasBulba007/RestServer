package com.tarasPlus.serverRest.service;

import com.tarasPlus.serverRest.model.Role;
import com.tarasPlus.serverRest.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(User user);

    User addUser(User user);

    boolean updateUser(User user) ;

    boolean notNullDataUser(User user);

    boolean isExistLogin(String login);

    public User findByLogin(String username);
}

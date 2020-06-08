package com.tarasPlus.serverRest.service;

import com.tarasPlus.serverRest.dao.RoleDao;
import com.tarasPlus.serverRest.dao.UserDao;
import com.tarasPlus.serverRest.model.Role;
import com.tarasPlus.serverRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Role userRole;
    private Role adminRole;
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean updateUser(User user) {
        if (notNullDataUser(user)) {
            userDao.save(user);
            System.out.println("Updated user");
            return true;

        }
        System.out.println("user not found");
        return false;
    }

    @Override
    public User addUser(User user) {
        if (!isExistLogin(user.getUsername())) {
            addUserRole(user);
             userDao.save(user);
             return user;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean isExistLogin(String login) {
        return (userDao.findByLogin(login) != null);
    }

    @Override
    public boolean notNullDataUser(User user) {
        return (user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty() &&
                user.getEmail() != null && !user.getEmail().isEmpty());
    }

    private void addUserRole(User user) {
        if (userRole == null) {
            userRole = roleDao.findById(2L);
        }
        user.getRoles().add(userRole);
    }

    private void initRole(User user, String access) {
        if (access.contains("ADMIN")) {
            addAdminRole(user);
        } else {
            addUserRole(user);
        }
    }

    private void addAdminRole(User user) {
        if (adminRole == null) {
            adminRole = roleDao.findById(1L);
        }
        user.getRoles().add(adminRole);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public User findByLogin(String username){
        return userDao.findByLogin(username);
    }
}

package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.IUserDao;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Service
public class UserService implements IUserService {


    private final IUserDao userDao;
    @Autowired
    public UserService(@Lazy IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    public User findByUsername(String username) {
            return  getAllUsers().stream().filter(prson -> prson.getUsername().equals(username)).findAny().orElse(null);
    }



}

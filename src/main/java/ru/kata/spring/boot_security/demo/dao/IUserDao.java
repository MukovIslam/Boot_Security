package ru.kata.spring.boot_security.demo.dao;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
public interface IUserDao {

    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser (int id);
    void updateUser(User user);
    void deleteAllUsers();
    User show(int id);
}


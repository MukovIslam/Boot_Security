package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface IDaoRole {
     List<Role> getAllRole();
     void addRole(Role role);
     void deleteAllRole();
     Role getRole(String strRol);
}

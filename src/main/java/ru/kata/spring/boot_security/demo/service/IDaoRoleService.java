package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface IDaoRoleService {
    public List<Role> getAllRole() ;
    public void addRole(Role role);
    public void deleteAllRole();
    public Role getRole(String strRol);
}

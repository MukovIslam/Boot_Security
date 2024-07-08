package ru.kata.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.IDaoRole;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

@Service
public class RoleService implements IDaoRoleService {

    private IDaoRole iDaoRole;

    public List<Role> getAllRole() {
        return iDaoRole.getAllRole();
    }

    public void addRole(Role role) {
        iDaoRole.addRole(role);
    }
    public void deleteAllRole() {
       iDaoRole.deleteAllRole();
    }

    @Override
    public Role getRole(String strRol) {
        return iDaoRole.getRole(strRol);
    }

    @Autowired
    public void setiDaoRole(IDaoRole iDaoRole) {
        this.iDaoRole = iDaoRole;
    }

}

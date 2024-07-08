package ru.kata.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class Init {

    private IUserService userDao;
    private IDaoRoleService daoRole;




    @Transactional
    @PostConstruct
    public void init() {
        userDao.deleteAllUsers();
        daoRole.deleteAllRole();

        Role role = new Role("ADMIN");
        Role role2 = new Role("USER");

        User user1 = new User("ivan","Петров","email1@mail.ru","11111");
        User user2 = new User("Коля","Сидоров","email2@mail.ru","22222");
        User user3 = new User("Петя","Иванов","email3@mail.ru","33333");
       // role.setUserRole(new ArrayList<>(List.of(user1)));
        user1.setRoles(new ArrayList<>(List.of(role)));
        user2.setRoles(new ArrayList<>(List.of(role2)));
        user3.setRoles(new ArrayList<>(List.of(role2)));

        daoRole.addRole(role2);
        daoRole.addRole(role);
        userDao.addUser(user3);
        userDao.addUser(user2);
        userDao.addUser(user1);

    }
    @Autowired
    public void setUserDao(IUserService userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setDaoRole(IDaoRoleService daoRole) {
        this.daoRole = daoRole;
    }

}

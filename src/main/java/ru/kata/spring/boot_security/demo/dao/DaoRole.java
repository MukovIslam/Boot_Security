package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class DaoRole implements IDaoRole {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Role> getAllRole() {
        System.out.println("сработал оллл Role ");
        for (Role role : em.createQuery("SELECT u FROM Role u", Role.class).getResultList()) {
            System.out.println(role.getName());
        }
        return em.createQuery("SELECT u FROM Role u", Role.class).getResultList();
    }
    @Override
    public void addRole(Role role) {
        em.persist(role);
    }
    @Override
    public void deleteAllRole() {
        em.createQuery("DELETE FROM Role").executeUpdate();
        em.createNativeQuery("ALTER TABLE test.roles AUTO_INCREMENT = 1").executeUpdate();
    }
    @Override
    public Role getRole(String strRol){
        return getAllRole().stream().filter(prson -> prson.getName().equals(strRol)).findAny().orElse(null);
    }
}
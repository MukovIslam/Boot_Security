package ru.kata.spring.boot_security.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager em;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserDao( @Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public List<User> getAllUsers() {
        System.out.println("сработал оллл юзер");
        for (User user : em.createQuery("SELECT u FROM User u", User.class).getResultList()) {
            System.out.println(user.getUsername());
        }
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        em.persist(user);

    }

    @Override
    public void deleteUser(int id) {
        System.out.println("сработал удолить");
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        System.out.println("сработал обновить");
        em.merge(user);
        System.out.println("закончил");
    }

    @Override
    public void deleteAllUsers() {
        em.createQuery("DELETE FROM User").executeUpdate();
        em.createNativeQuery("ALTER TABLE test.users_mvc AUTO_INCREMENT = 1").executeUpdate();

    }

    @Override
    public User show(int id) {
        return getAllUsers().stream().filter(prson -> prson.getId() == id).findAny().orElse(null);
    }


}

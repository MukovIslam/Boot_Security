package ru.kata.spring.boot_security.demo.securityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.IUserService;

import javax.transaction.Transactional;

@Service
public class UserSecurityService implements UserDetailsService {
    private IUserService iUserService;

    @Autowired
    public UserSecurityService(@Lazy IUserService iUserService) {
        this.iUserService = iUserService;
    }

    public User findByUsername(String username) {
        return iUserService.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(" нет у нас таких ", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

}

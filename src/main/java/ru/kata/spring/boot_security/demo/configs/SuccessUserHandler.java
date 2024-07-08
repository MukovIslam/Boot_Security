package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.securityService.UserSecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    UserSecurityService userSecurityService;

    @Autowired
    public SuccessUserHandler(@Lazy UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        int id = userSecurityService.findByUsername(userDetails.getUsername()).getId();


        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin");

        } else if
        (roles.contains("USER")) {
            httpServletResponse.sendRedirect("/user/" + id);

        } else {
            httpServletResponse.sendRedirect("/");
        }

    }


}
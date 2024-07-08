package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "users_mvc")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя может содержать от 2 до 30 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Фамилия может содержать от 2 до 30 символов")
    @Column(name = "lastname")
    private String lastName;

    @NotEmpty(message = "Поле не должно быть пустым")
   // @Size(min = 3, max = 300, message = "Пароль должен  содержать от 2 до 300 символов")
    @Column(name = "password" )
    private String password;

    @Email(message = "Не корректный Email")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name = "email")
    private String email;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name =  ("user_role"),
            joinColumns = @JoinColumn(name = "user_id" ),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public User(String firstName, String lastName, String email, String password) {
        this.username = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password ;
    }

    public User(){}



    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }


    public @NotEmpty(message = "Поле не должно быть пустым") @Size(min = 2, max = 30, message = "Имя может содержать от 2 до 30 символов") String getUsername() {
        return username;
    }
    public void setUsername(@NotEmpty(message = "Поле не должно быть пустым") @Size(min = 2, max = 30, message = "Имя может содержать от 2 до 30 символов") String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void setRole(Role role) {
        roles.add(role);
    }


    public void setPassword(@NotEmpty(message = "Поле не должно быть пустым") @Size(min = 3, max = 30, message = "Пароль должен  содержать от 2 до 30 символов") String password) {
        this.password = password;
    }




    public String getPassword() {
        return password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastName, password, email, roles);
    }
}
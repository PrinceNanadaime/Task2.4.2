package web.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Component
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Column(name = "lastname")
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "last name should be between 2 and 30 characters")
    private String lastName;
    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater then 0")
    private byte age;
    @Column(name = "username")
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User(int id, String username, String lastName, byte age, String name, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public User(int id, String username, String lastName, byte age, String name, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }


    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Role: " +  roles;
    }
}

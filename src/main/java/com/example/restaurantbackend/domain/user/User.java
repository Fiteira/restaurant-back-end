package com.example.restaurantbackend.domain.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String password;

    private UserRole role;

    /**
     * Get user authorities according to their role
     * @return user authorities collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CHEF"), new SimpleGrantedAuthority("ROLE_WAITER"));
        else if (this.role == UserRole.CHEF) return List.of(new SimpleGrantedAuthority("ROLE_CHEF"));
        else if (this.role == UserRole.WAITER) return List.of(new SimpleGrantedAuthority("ROLE_WAITER"));
        else return null;
    }



    @Override
    public String getUsername() {
        return name;
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


    /**
     * Empty constructor to create a new User object configured with Builder pattern
     */
    public static User UserBuilder() {
        return new User();
    }

    /**
     * Builder User for name
     *
     * @param name user name
     * @return user with name
     */
    public User name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Builder User for password
     *
     * @param password user password
     * @return user with password
     */
    public User password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Builder User for role
     *
     * @param role user role
     * @return user with role
     */
    public User role(UserRole role) {
        this.role = role;
        return this;
    }

    /**
     * Get User ID
     *
     * @return user id
     */
    public String getId() {
        return id;
    }

    /**
     * Set User ID
     *
     * @param id user id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get Username
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Set UserName
     *
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get User Password
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set User Password
     *
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get User Role
     *
     * @return user role
     */
    public String getRole() {
        return role.getRole();
    }

    /**
     * Set User Role
     *
     * @param role user role
     */
    public void setRole(String role) {
        this.role = UserRole.valueOf(role);
    }
}

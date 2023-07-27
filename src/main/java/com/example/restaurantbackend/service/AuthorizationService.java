package com.example.restaurantbackend.service;

import com.example.restaurantbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    final
    UserRepository repository;

    /**
     * Constructor
     * @param repository UserRepository
     */
    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to load user by username
     * @param username String
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = repository.findByName(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}

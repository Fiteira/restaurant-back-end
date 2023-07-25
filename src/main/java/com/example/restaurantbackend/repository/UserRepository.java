package com.example.restaurantbackend.repository;

import com.example.restaurantbackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
   Optional <UserDetails> findByName(String name);

}

package com.example.restaurantbackend.repository;


import com.example.restaurantbackend.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

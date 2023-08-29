package com.example.restaurantbackend.repository;

import com.example.restaurantbackend.domain.TableFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableFoodRepository extends JpaRepository<TableFood, Long> {
}

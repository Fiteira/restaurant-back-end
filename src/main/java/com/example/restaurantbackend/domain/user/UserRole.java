package com.example.restaurantbackend.domain.user;

public enum UserRole {
    ADMIN("admin"),
    CHEF("chef"),
    WAITER("waiter");

    private String role;


    /**
     * Constructor for UserRole
     * @param role
     */
    UserRole(String role) {
        this.role = role;
    }

    /**
     * Getter for role
     * @return role
     */
    public String getRole() {
        return role;
    }
}

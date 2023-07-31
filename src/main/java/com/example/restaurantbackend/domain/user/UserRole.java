package com.example.restaurantbackend.domain.user;

public enum UserRole {
    ADMIN("admin"),
    CHEF("chef"),
    WAITER("waiter");

    private final String role;


    /**
     * Constructor for UserRole
     * requires role as String
     */
    UserRole(final String role) {
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

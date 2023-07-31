package com.example.restaurantbackend.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tableFood")
public class TableFood {
    @Id
    private int id;

    private int positionX;

    private int positionY;

    /**
     * Empty constructor to create a new TableFood object configured with Builder pattern
     */
    public static TableFood TableFoodBuilder() {
        return new TableFood();
    }

    public TableFood id(int id) {
        this.id = id;
        return this;
    }

    public TableFood positionX(int positionX) {
        this.positionX = positionX;
        return this;
    }

    public TableFood positionY(int positionY) {
        this.positionY = positionY;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}

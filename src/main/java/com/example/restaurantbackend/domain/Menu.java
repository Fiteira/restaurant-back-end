package com.example.restaurantbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private String ingredients;

    private String type;

    private String restriction;

    private float price;

    private String image;


    /**
     * Empty constructor to create a new Menu object configured with Builder pattern
     */
    public static Menu MenuBuilder() {
        return new Menu();
    }

    public Menu id(int id) {
        this.id = id;
        return this;
    }

    public Menu name(String name) {
        this.name = name;
        return this;
    }

    public Menu ingredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Menu type(String type) {
        this.type = type;
        return this;
    }

    public Menu restriction(String restriction) {
        this.restriction = restriction;
        return this;
    }

    public Menu price(float price) {
        this.price = price;
        return this;
    }

    public Menu image(String image) {
        this.image = image;
        return this;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

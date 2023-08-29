package com.example.restaurantbackend.domain;


import com.example.restaurantbackend.domain.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "requestFood")
public class RequestFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tableId")
    private TableFood tableFood;

    @ManyToMany
    @JoinTable(
            name = "requestMenu", // name of the intermediate table
            joinColumns = @JoinColumn(name = "requestFoodID"), // column key foreign reference to RequestFood
            inverseJoinColumns = @JoinColumn(name = "menuId") // column key foreign reference to Menu
    )
    private List<Menu> menu;


    /**
     * Empty constructor to create a new RequestFood object configured with Builder pattern
     */
    public static RequestFood RequestFoodBuilder() {
        return new RequestFood();
    }

    /**
     * Builder RequestFood for id
     *
     * @param id user id
     * @return user with id
     */
    public RequestFood id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Builder RequestFood for idUser
     *
     * @param idUser user id
     * @return user with idUser
     */
    public RequestFood user(User idUser) {
        this.user = idUser;
        return this;
    }

    /**
     * Builder RequestFood for tableFood
     *
     * @param tableFood user id
     * @return user with tableFood
     */
    public RequestFood tableFood(TableFood tableFood) {
        this.tableFood = tableFood;
        return this;
    }

    /**
     * Builder RequestFood for menu
     *
     * @param menu user id
     * @return user with menu
     */
    public RequestFood menu(List<Menu> menu) {
        this.menu = menu;
        return this;
    }

    /**
     * Get RequestFood ID
     *
     * @return RequestFood id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set RequestFood ID
     *
     * @param id RequestFood id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get RequestFood idUser
     *
     * @return RequestFood idUser
     */
    public User getIdUser() {
        return user;
    }

    /**
     * Set RequestFood idUser
     *
     * @param user RequestFood object User
     */
    public void setIdUser(User user) {
        this.user = user;
    }

    /**
     * Get RequestFood tableFood
     *
     * @return RequestFood tableFood
     */
    public TableFood getTableFood() {
        return tableFood;
    }

    /**
     * Set RequestFood tableFood
     *
     * @param tableFood RequestFood tableFood
     */
    public void setTableFood(TableFood tableFood) {
        this.tableFood = tableFood;
    }

    /**
     * Get RequestFood menu
     *
     * @return RequestFood menu
     */
    public List<Menu> getMenu() {
        return menu;
    }

    /**
     * Set RequestFood menu
     *
     * @param menu RequestFood menu
     */
    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}

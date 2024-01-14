package org.theoliverlear.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    //============================-Constructors-==============================
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //=============================-Getters-==================================
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public Long getId() {
        return this.id;
    }
    //=============================-Setters-==================================
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
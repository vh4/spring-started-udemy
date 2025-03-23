package com.mvc.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "User Id cannot be empty")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "role")
    private String role;

    public Roles(String role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public Roles(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role='" + role + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}


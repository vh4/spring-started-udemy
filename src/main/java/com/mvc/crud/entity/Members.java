package com.mvc.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id; // ✅ Ensure @Id annotation is used
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "members")
public class Members {

    @Id // ✅ Specify that `user_id` is the primary key
    @NotBlank(message = "User Id cannot be empty")
    @Column(name = "user_id")
    private String userId; // Change to camelCase to match Java conventions

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "pw")
    private String password; // Change to camelCase

    // ✅ Add Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

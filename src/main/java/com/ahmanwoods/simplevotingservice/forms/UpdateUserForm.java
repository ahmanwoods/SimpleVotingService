package com.ahmanwoods.simplevotingservice.forms;

import javax.validation.constraints.NotBlank;


public class UpdateUserForm {
    @NotBlank(message="Username cannot be empty")
    private String username;
    @NotBlank(message="New username cannot be empty")
    private String newUsername;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}

package com.ahmanwoods.simplevotingservice.forms;


import javax.validation.constraints.NotBlank;

public class DeleteUserForm {
    @NotBlank(message="Username cannot be empty")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

package com.ahmanwoods.simplevotingservice.forms;

public class DeleteUserForm {

    private String username;

    public DeleteUserForm() {

    }

    public DeleteUserForm(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

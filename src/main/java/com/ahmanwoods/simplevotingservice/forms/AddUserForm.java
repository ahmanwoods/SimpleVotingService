package com.ahmanwoods.simplevotingservice.forms;

public class AddUserForm {

    private String username;

    public AddUserForm() {

    }

    public AddUserForm(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

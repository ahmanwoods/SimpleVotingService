package com.ahmanwoods.simplevotingservice.forms;

import org.hibernate.sql.Update;

public class UpdateUserForm {

    private String username;
    private String newUsername;

    public UpdateUserForm() {

    }

    public UpdateUserForm(String username, String newUsername) {
        this.username = username;
        this.newUsername = newUsername;
    }

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

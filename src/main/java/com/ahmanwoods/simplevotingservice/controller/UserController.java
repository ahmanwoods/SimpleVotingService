package com.ahmanwoods.simplevotingservice.controller;

import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.forms.AddUserForm;
import com.ahmanwoods.simplevotingservice.forms.DeleteUserForm;
import com.ahmanwoods.simplevotingservice.forms.UpdateUserForm;
import com.ahmanwoods.simplevotingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public ResponseEntity<UserEntity> addUser(@Valid @RequestBody AddUserForm addUserForm, HttpServletResponse response) {
        UserEntity createdUser = userService.addUser(addUserForm.getUsername());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.POST, value="/login")
    public ResponseEntity<UserEntity> loginUser(@Valid @RequestBody AddUserForm loginUserForm, HttpServletResponse response) {
        UserEntity loginUser = userService.loginUser(loginUserForm.getUsername());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete")
    public ResponseEntity<Void> deleteUser(@Valid @RequestBody DeleteUserForm deleteUserForm) {
        UserEntity deletedUser = userService.deleteUser(deleteUserForm.getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/update")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserForm updateUserForm) {
        UserEntity updatedUser = userService.updateUser(updateUserForm.getUsername(), updateUserForm.getNewUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

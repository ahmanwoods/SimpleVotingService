package com.ahmanwoods.simplevotingservice.controller;

import com.ahmanwoods.simplevotingservice.apierror.ApiError;
import com.ahmanwoods.simplevotingservice.entity.UserEntity;
import com.ahmanwoods.simplevotingservice.exception.UsernameInUseException;
import com.ahmanwoods.simplevotingservice.forms.AddUserForm;
import com.ahmanwoods.simplevotingservice.forms.DeleteUserForm;
import com.ahmanwoods.simplevotingservice.forms.UpdateUserForm;
import com.ahmanwoods.simplevotingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public ResponseEntity<Void> addUser(@RequestBody AddUserForm addUserForm) {
        UserEntity createdUser = userService.addUser(addUserForm.getUsername());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody DeleteUserForm deleteUserForm) {
        UserEntity deletedUser = userService.deleteUser(deleteUserForm.getUsername());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/update")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserForm updateUserForm) {
        UserEntity updatedUser = userService.updateUser(updateUserForm.getUsername(), updateUserForm.getNewUsername());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

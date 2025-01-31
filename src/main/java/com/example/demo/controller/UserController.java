package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginInfo;
import com.example.demo.service.UserService;
import com.example.demo.exceptions.AuthenticationFailed;

@RestController
public class UserController {

    private static Logger userLogger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ExceptionHandler
    public ResponseEntity<String> notAuthorized(AuthenticationFailed e) {
        userLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // @PostMapping("/register")
    // public ResponseEntity<String> createUser(@RequestBody User newUser) {
    //     return new ResponseEntity<>(this.userService.createUser(newUser), HttpStatus.CREATED);
    // }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginInfo credentials, HttpSession session){
        ResponseEntity<String> response = new ResponseEntity<>(this.userService.authenticate(credentials),HttpStatus.ACCEPTED);
        session.setAttribute("user", credentials.getUsername());
        return response;   
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("logged out", HttpStatus.OK);
    }
}

package org.theoliverlear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.comm.login.LoginRequest;
import org.theoliverlear.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<String> login(@PathVariable LoginRequest loginRequest) {
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
}

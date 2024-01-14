package org.theoliverlear.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.model.user.login.LoginRequest;

@Controller
@RequestMapping("/login")
public class UserController {
    @RequestMapping("/")
    public ResponseEntity<String> login(@PathVariable LoginRequest loginRequest) {
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
}

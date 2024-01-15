package org.theoliverlear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.comm.login.CredentialRequest;
import org.theoliverlear.entity.User;
import org.theoliverlear.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends SudokuController {
    User currentUser;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialRequest credentialRequest) {
        String username = credentialRequest.getUsername();
        String password = credentialRequest.getPassword();
        boolean containsUser = this.userService.containsUser(username);
        boolean emptyUsername = username.equals("");
        boolean emptyPassword = password.equals("");
        if (!containsUser) {
            return new ResponseEntity<>("user does not exist", HttpStatus.BAD_REQUEST);
        } else if (emptyUsername || emptyPassword) {
            return new ResponseEntity<>("empty username or password", HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getUser(username);
        boolean passwordMatch = this.userService.passwordMatch(password, user.getPassword());
        if (!passwordMatch) {
            return new ResponseEntity<>("incorrect password", HttpStatus.BAD_REQUEST);
        }
        this.currentUser = user;
        return new ResponseEntity<>("authenticated", HttpStatus.OK);
    }

    @RequestMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CredentialRequest credentialRequest) {
        String username = credentialRequest.getUsername();
        String password = credentialRequest.getPassword();
        boolean containsUser = this.userService.containsUser(username);
        boolean emptyUsername = username.equals("");
        boolean emptyPassword = password.equals("");
        if (emptyUsername || emptyPassword) {
            return new ResponseEntity<>("empty username or password", HttpStatus.BAD_REQUEST);
        } else if (containsUser) {
            return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
        }
        this.userService.saveUser(new User(username, password));
        this.currentUser = this.userService.getUser(username);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @RequestMapping("/logout")
    public ResponseEntity<String> logout() {
        this.currentUser = null;
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @RequestMapping("/logged-in")
    public ResponseEntity<String> loggedIn() {
        if (this.currentUser == null) {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
        return new ResponseEntity<>("true", HttpStatus.OK);
    }
    @RequestMapping("/refresh/account-button")
    public String refreshAccountButton(Model model) {
        System.out.println("Refreshing account button for: " + this.currentUser.getUsername());
        model.addAttribute("account_button", this.currentUser.getUsername());
        return "account-button-patch :: account-button-patch";
    }
    @RequestMapping("/save")
    public ResponseEntity<String> save() {
        this.userService.saveUser(this.currentUser);
        this.userService.saveBoard(this.sudokuService.getSudoku().getBoard());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}

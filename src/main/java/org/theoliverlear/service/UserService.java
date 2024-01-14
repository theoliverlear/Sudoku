package org.theoliverlear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.theoliverlear.model.user.login.LoginRequest;
import org.theoliverlear.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean repositoryContainsUser(LoginRequest loginRequest) {
        return false;
    }
    public boolean passwordMatches(LoginRequest loginRequest) {
        return false;
    }
    public boolean login(LoginRequest loginRequest) {
        return false;
    }
    public boolean signup(LoginRequest loginRequest) {
        return false;
    }
    //=============================-Getters-==================================
    public UserRepository getUserRepository() {
        return this.userRepository;
    }
    public BCryptPasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }
    //=============================-Setters-==================================
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}

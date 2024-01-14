package org.theoliverlear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.theoliverlear.comm.login.LoginRequest;
import org.theoliverlear.repository.UserRepository;

@Service
public class UserService {
    //=============================-Variables-================================
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    //============================-Constructors-==============================
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //=============================-Methods-==================================

    //---------------------------Contains-User--------------------------------
    public boolean repositoryContainsUser(LoginRequest loginRequest) {
        return false;
    }
    //---------------------------Password-Matches-----------------------------
    public boolean passwordMatches(LoginRequest loginRequest) {
        return false;
    }
    //-------------------------------Login------------------------------------
    public boolean login(LoginRequest loginRequest) {
        return false;
    }
    //-------------------------------Signup-----------------------------------
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

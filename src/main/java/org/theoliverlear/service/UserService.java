package org.theoliverlear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.theoliverlear.entity.Board;
import org.theoliverlear.entity.User;
import org.theoliverlear.repository.BoardRepository;
import org.theoliverlear.repository.UserRepository;

@Service
public class UserService {
    //=============================-Variables-================================
    private UserRepository userRepository;
    private BoardRepository boardRepository;
    private BCryptPasswordEncoder passwordEncoder;

    //============================-Constructors-==============================
    @Autowired
    public UserService(UserRepository userRepository,
                       BoardRepository boardRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //=============================-Methods-==================================

    //-----------------------------Save-User----------------------------------
    public void saveUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
    }
    //-----------------------------Save-Board---------------------------------
    public void saveBoard(Board board) {
        this.boardRepository.save(board);
    }
    //---------------------------Contains-User--------------------------------
    public boolean containsUser(String username) {
        return this.userRepository.getUserByUsername(username) != null;
    }
    //--------------------------Password-Match--------------------------------
    public boolean passwordMatch(String password, String encodedPassword) {
        boolean passwordsMatch = this.passwordEncoder.matches(password, encodedPassword);
        return passwordsMatch;
    }
    //-----------------------------Get-User-----------------------------------
    public User getUser(String username) {
        return this.userRepository.getUserByUsername(username);
    }
    //==============================-Getters-=================================
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

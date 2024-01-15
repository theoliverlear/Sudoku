package org.theoliverlear.entity;

import jakarta.persistence.*;
import org.theoliverlear.entity.convert.BoardJsonConverter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
//    @Convert(converter = BoardJsonConverter.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_board_id", referencedColumnName = "id")
    private Board currentBoard;
    //============================-Constructors-==============================
    public User() {
        this.username = "";
        this.password = "";
        this.currentBoard = new Board();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.currentBoard = new Board();
    }
    public User(String username, String password, Board currentBoard) {
        this.username = username;
        this.password = password;
        this.currentBoard = currentBoard;
    }
    //=============================-Getters-==================================
    public Long getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public Board getCurrentBoard() {
        return this.currentBoard;
    }
    //=============================-Setters-==================================
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }
}

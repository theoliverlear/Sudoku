package org.theoliverlear.repository;


import org.intellij.lang.annotations.Language;
import org.springframework.stereotype.Repository;
import org.theoliverlear.entity.Board;
import org.theoliverlear.entity.User;
import org.theoliverlear.model.sudoku.BoardIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserRepository extends SudokuRepository {
    //===========================-Constructors-===============================
    public UserRepository() {
        super();
    }
    //=============================-Methods-==================================

    //-----------------------------Save-User----------------------------------
    public void saveUser(User user) {
        @Language("SQL")
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //---------------------------Contains-User--------------------------------
    public boolean containsUser(User user) {
        @Language("SQL")
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            return statement.executeQuery().next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //-------------------------Save-Current-Board-----------------------------
    public void saveCurrentBoard(User user, Board board, ArrayList<BoardIndex> mutedIndices) {

    }
}

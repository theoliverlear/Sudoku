package org.theoliverlear.repository.db;

import org.springframework.stereotype.Repository;
import org.theoliverlear.entity.Board;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//@Repository
@Deprecated
public class BoardDatabase extends SudokuDatabase {
    //===========================-Constructors-===============================
    public BoardDatabase() {
        super();
    }
    //=============================-Methods-==================================

    //---------------------------Contains-Board-------------------------------
    public boolean containsBoard(Long id) {
        String sql = "SELECT * FROM boards WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeQuery().next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public boolean containsBoard(Board board) {
        Long id = board.getId();
        return this.containsBoard(id);
    }

}

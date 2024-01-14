package org.theoliverlear.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class SudokuRepository {
    //============================-Variables-=================================
    @Autowired
    DataSource dataSource;
    Connection connection;
    //===========================-Constructors-===============================
    public SudokuRepository() {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package org.theoliverlear.repository.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//@Repository
@Deprecated
public class SudokuDatabase {
    //============================-Variables-=================================
//    @Autowired
    DataSource dataSource;
    Connection connection;
    //===========================-Constructors-===============================
    public SudokuDatabase() {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

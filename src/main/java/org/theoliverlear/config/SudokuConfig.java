package org.theoliverlear.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SudokuConfig {
    //=============================-Beans-====================================
    @Bean
    public DataSource getDataSource() {
        // Get the values needed to connect from the environment variables.
        final String USER = System.getenv("H2_USER");
        final String PASSWORD = System.getenv("H2_PW");
        final String URL = "jdbc:h2:C:\\Users\\olive\\GitHub\\Sudoku\\db\\sudoku_db\\sudoku_db";
        // Return a DataSource built with a factory method with the values
        // from the environment variables.
        return DataSourceBuilder.create()
                                .driverClassName("org.h2.Driver")
                                .url(URL)
                                .username(USER)
                                .password(PASSWORD)
                                .build();

    }
}

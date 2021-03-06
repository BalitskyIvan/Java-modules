package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase dataSource;
    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .addScripts("schema.sql", "data.sql")
                .generateUniqueName(true)
                .build();
    }

    @Test
    void testConnection() throws SQLException {
        try {
            Assertions.assertNotEquals(dataSource.getConnection(), null);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}

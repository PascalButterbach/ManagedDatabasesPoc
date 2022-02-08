package org.butterbach.manageddatabasespoc.rowMapper;

import org.butterbach.manageddatabasespoc.model.DatabaseConnection;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class DatabaseConnectionRowMapper implements RowMapper<DatabaseConnection> {
    @Override
    public DatabaseConnection map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new DatabaseConnection(
                UUID.fromString(rs.getString("tenant_uuid")),
                rs.getString("url"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}

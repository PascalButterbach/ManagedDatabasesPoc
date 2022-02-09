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

        return DatabaseConnection.builder()
                .tenantUuid(UUID.fromString(rs.getString("tenant_uuid")))
                .url(rs.getString("url"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
    }
}

package org.butterbach.manageddatabasespoc.rowMapper;

import org.butterbach.manageddatabasespoc.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {

        return User.builder()
                .uuid(UUID.fromString(rs.getString("uuid")))
                .name(rs.getString("name"))
                .build();
    }
}

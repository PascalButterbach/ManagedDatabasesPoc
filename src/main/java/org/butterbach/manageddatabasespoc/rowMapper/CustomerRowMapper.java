package org.butterbach.manageddatabasespoc.rowMapper;

import org.butterbach.manageddatabasespoc.model.Customer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Customer(
                UUID.fromString(rs.getString("uuid")),
                rs.getString("name")
        );
    }
}

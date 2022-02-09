package org.butterbach.manageddatabasespoc.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Builder
public class DatabaseConnection {

    private final UUID tenantUuid;
    private final String url;
    private final String username;
    private final String password;

    public DataSource getAsDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.getUrl());
        dataSource.setUsername(this.getUsername());
        dataSource.setPassword(this.getPassword());
        return dataSource;
    }
}

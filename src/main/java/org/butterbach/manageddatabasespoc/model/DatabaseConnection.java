package org.butterbach.manageddatabasespoc.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.UUID;

public class DatabaseConnection {

    private final UUID tenantUuid;
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConnection(UUID tenantUuid, String url, String username, String password) {
        this.tenantUuid = tenantUuid;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public UUID getTenantUuid() {
        return tenantUuid;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DataSource getAsDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.getUrl());
        dataSource.setUsername(this.getUsername());
        dataSource.setPassword(this.getPassword());
        return dataSource;
    }
}

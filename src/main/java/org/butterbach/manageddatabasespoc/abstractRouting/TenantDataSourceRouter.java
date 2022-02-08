package org.butterbach.manageddatabasespoc.abstractRouting;

import org.butterbach.manageddatabasespoc.model.DatabaseConnection;
import org.butterbach.manageddatabasespoc.service.DatabaseConnectionService;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TenantDataSourceRouter extends AbstractRoutingDataSource {

    private final TenantDatabaseContextHolder tenantDatabaseContextHolder;
    private final DatabaseConnectionService databaseConnectionService;

    public TenantDataSourceRouter(TenantDatabaseContextHolder tenantDatabaseContextHolder,
                                  DatabaseConnectionService databaseConnectionService) {

        this.tenantDatabaseContextHolder = tenantDatabaseContextHolder;
        this.databaseConnectionService = databaseConnectionService;

        ArrayList<DatabaseConnection> tenantConnectionDetails = databaseConnectionService.query();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        tenantConnectionDetails.forEach(databaseConnection -> {
            dataSourceMap.put(databaseConnection.getTenantUuid(), databaseConnection.getAsDatasource());
        });

        this.setTargetDataSources(dataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantDatabaseContextHolder.getTenantDatabase();
    }

}

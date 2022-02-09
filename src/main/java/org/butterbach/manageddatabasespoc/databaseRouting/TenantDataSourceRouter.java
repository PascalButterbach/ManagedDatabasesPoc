package org.butterbach.manageddatabasespoc.databaseRouting;

import org.butterbach.manageddatabasespoc.model.DatabaseConnection;
import org.butterbach.manageddatabasespoc.service.DatabaseConnectionService;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TenantDataSourceRouter extends AbstractRoutingDataSource {

    public TenantDataSourceRouter(DatabaseConnectionService databaseConnectionService) {

        Map<Object, Object> dataSourceMap = databaseConnectionService
                .query()
                .stream()
                .collect(Collectors.toMap(DatabaseConnection::getTenantUuid, DatabaseConnection::getAsDatasource));

        this.setTargetDataSources(dataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantDatabaseContextHolder.getTenantDatabase();
    }

}

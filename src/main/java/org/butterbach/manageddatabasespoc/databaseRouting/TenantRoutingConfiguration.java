package org.butterbach.manageddatabasespoc.databaseRouting;

import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@DependsOn("tenantDataSourceRouter")
public class TenantRoutingConfiguration {

    private final TenantDataSourceRouter tenantDataSourceRouter;

    @Bean
    @Primary
    public DataSource dataSource() {
        return tenantDataSourceRouter;
    }

    @Bean("tenant_jdbi")
    public Jdbi jdbi(List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) throws SQLException {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource());
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);
        return jdbi;
    }
}

package org.butterbach.manageddatabasespoc.databaseRouting;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TenantDatabaseContextHolder {

    private static final ThreadLocal<UUID> CONTEXT = new ThreadLocal<>();

    public static void set(UUID tenantUuid) {
        if (tenantUuid == null)
            throw new IllegalArgumentException("tenant uuid cannot be null");

        CONTEXT.set(tenantUuid);
    }

    public static UUID getTenantDatabase() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

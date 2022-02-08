package org.butterbach.manageddatabasespoc;

import org.butterbach.manageddatabasespoc.abstractRouting.TenantDatabaseContextHolder;
import org.butterbach.manageddatabasespoc.model.Customer;
import org.butterbach.manageddatabasespoc.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CmdRunner implements CommandLineRunner {

    private final CustomerService customerService;

    public CmdRunner(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void run(String... args) throws Exception {

        UUID uuid = UUID.fromString("11111111-1111-1111-1111-111111111111");
        //UUID uuid = UUID.fromString("22222222-2222-2222-2222-222222222222");


        TenantDatabaseContextHolder.set(uuid);
        customerService.create(new Customer(null, "new one"));
        TenantDatabaseContextHolder.clear();
    }
}

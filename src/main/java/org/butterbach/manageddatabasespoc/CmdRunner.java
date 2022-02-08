package org.butterbach.manageddatabasespoc;

import org.butterbach.manageddatabasespoc.abstractRouting.TenantDatabaseContextHolder;
import org.butterbach.manageddatabasespoc.model.Customer;
import org.butterbach.manageddatabasespoc.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class CmdRunner implements CommandLineRunner {

    private final CustomerService customerService;

    public CmdRunner(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void run(String... args) {

        ArrayList<Thread> tenantOneThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tenantOneThreads.add(
                    new Thread(() -> {
                        for (int j = 0; j < 1000; j++) {

                            UUID uuid1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
                            TenantDatabaseContextHolder.set(uuid1);
                            customerService.create(new Customer(null, LocalDateTime.now().toString()));
                            TenantDatabaseContextHolder.clear();
                        }
                    })
            );
        }

        ArrayList<Thread> tenantTwoThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tenantTwoThreads.add(
                    new Thread(() -> {
                        for (int j = 0; j < 1000; j++) {

                            UUID uuid1 = UUID.fromString("22222222-2222-2222-2222-222222222222");
                            TenantDatabaseContextHolder.set(uuid1);
                            customerService.create(new Customer(null, LocalDateTime.now().toString()));
                            TenantDatabaseContextHolder.clear();
                        }
                    })
            );
        }

        tenantOneThreads.forEach(Thread::start);
        tenantTwoThreads.forEach(Thread::start);
    }
}

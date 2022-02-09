package org.butterbach.manageddatabasespoc;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.butterbach.manageddatabasespoc.databaseRouting.TenantDatabaseContextHolder;
import org.butterbach.manageddatabasespoc.model.Customer;
import org.butterbach.manageddatabasespoc.model.User;
import org.butterbach.manageddatabasespoc.service.CustomerService;
import org.butterbach.manageddatabasespoc.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CmdRunner implements CommandLineRunner {

    private final CustomerService customerService;
    private final UserService userService;

    private final int THREAD_ITERATIONS = 1000;
    private final int THREAD_SLEEP_BOUNDARY = 1000;
    private final int THREADS = 5;
    private final boolean PRINT = false;

    @SneakyThrows
    @Override
    public void run(String... args) {

        createCustomerThreads(UUID.fromString("11111111-1111-1111-1111-111111111111")).forEach(Thread::start);
        createCustomerThreads(UUID.fromString("22222222-2222-2222-2222-222222222222")).forEach(Thread::start);
        createCustomerThreads(UUID.fromString("33333333-3333-3333-3333-333333333333")).forEach(Thread::start);

        retrieveCustomerThreads(UUID.fromString("11111111-1111-1111-1111-111111111111")).forEach(Thread::start);
        retrieveCustomerThreads(UUID.fromString("22222222-2222-2222-2222-222222222222")).forEach(Thread::start);
        retrieveCustomerThreads(UUID.fromString("33333333-3333-3333-3333-333333333333")).forEach(Thread::start);

        createUserThreads(UUID.fromString("11111111-1111-1111-1111-111111111111")).forEach(Thread::start);
        createUserThreads(UUID.fromString("22222222-2222-2222-2222-222222222222")).forEach(Thread::start);
        createUserThreads(UUID.fromString("33333333-3333-3333-3333-333333333333")).forEach(Thread::start);

        retrieveUserThreads(UUID.fromString("11111111-1111-1111-1111-111111111111")).forEach(Thread::start);
        retrieveUserThreads(UUID.fromString("22222222-2222-2222-2222-222222222222")).forEach(Thread::start);
        retrieveUserThreads(UUID.fromString("33333333-3333-3333-3333-333333333333")).forEach(Thread::start);
    }

    private ArrayList<Thread> createCustomerThreads(UUID tenantUuid) {
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            threadList.add(this.createCustomerThreadForTenant(tenantUuid));
        }

        return threadList;
    }

    private Thread createCustomerThreadForTenant(UUID tenantUuid) {
        return new Thread(() -> {
            for (int j = 0; j < THREAD_ITERATIONS; j++) {

                TenantDatabaseContextHolder.set(tenantUuid);

                Customer customer = customerService.create(
                        Customer.builder()
                                .uuid(UUID.randomUUID())
                                .name(String.format("[%s]%s", tenantUuid.toString().substring(0, 8), LocalDateTime.now()))
                                .build());

                TenantDatabaseContextHolder.clear();

                if (PRINT)
                    System.out.println(customer + " created.");

                try {
                    Thread.sleep((long) (Math.random() * THREAD_SLEEP_BOUNDARY));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private ArrayList<Thread> retrieveCustomerThreads(UUID tenantUuid) {
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            threadList.add(this.retrieveCustomerThreadForTenant(tenantUuid));
        }

        return threadList;
    }

    private Thread retrieveCustomerThreadForTenant(UUID tenantUuid) {
        return new Thread(() -> {
            for (int j = 0; j < THREAD_ITERATIONS; j++) {

                TenantDatabaseContextHolder.set(tenantUuid);

                Customer customer = customerService.retrieveRandomRow();

                TenantDatabaseContextHolder.clear();

                if (PRINT)
                    System.out.println(customer + " retrieved.");

                try {
                    Thread.sleep((long) (Math.random() * THREAD_SLEEP_BOUNDARY));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private ArrayList<Thread> createUserThreads(UUID tenantUuid) {
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            threadList.add(this.createUserThreadForTenant(tenantUuid));
        }

        return threadList;
    }

    private Thread createUserThreadForTenant(UUID tenantUuid) {
        return new Thread(() -> {
            for (int j = 0; j < THREAD_ITERATIONS; j++) {

                TenantDatabaseContextHolder.set(tenantUuid);

                User user = userService.create(
                        User.builder()
                                .uuid(UUID.randomUUID())
                                .name(String.format("[%s]%s", tenantUuid.toString().substring(0, 8), LocalDateTime.now()))
                                .build());

                TenantDatabaseContextHolder.clear();

                if (PRINT)
                    System.out.println(user + " created.");

                try {
                    Thread.sleep((long) (Math.random() * THREAD_SLEEP_BOUNDARY));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private ArrayList<Thread> retrieveUserThreads(UUID tenantUuid) {
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            threadList.add(this.retrieveUserThreadForTenant(tenantUuid));
        }

        return threadList;
    }

    private Thread retrieveUserThreadForTenant(UUID tenantUuid) {
        return new Thread(() -> {
            for (int j = 0; j < THREAD_ITERATIONS; j++) {

                TenantDatabaseContextHolder.set(tenantUuid);

                User user = userService.retrieveRandomRow();

                TenantDatabaseContextHolder.clear();

                if (PRINT)
                    System.out.println(user + " retrieved.");

                try {
                    Thread.sleep((long) (Math.random() * THREAD_SLEEP_BOUNDARY));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

package org.butterbach.manageddatabasespoc.model;

import java.util.UUID;

public class Customer {

    private final UUID uuid;
    private final String name;

    public Customer(UUID uuid, String name) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}

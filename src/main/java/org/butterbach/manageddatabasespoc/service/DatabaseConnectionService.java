package org.butterbach.manageddatabasespoc.service;

import org.butterbach.manageddatabasespoc.dao.DatabaseConnectionDao;
import org.butterbach.manageddatabasespoc.model.DatabaseConnection;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DatabaseConnectionService {

    private final DatabaseConnectionDao databaseConnectionDao;

    public DatabaseConnectionService(@Qualifier("default_jdbi") Jdbi jdbi) {
        this.databaseConnectionDao = jdbi.onDemand(DatabaseConnectionDao.class);
    }

    public ArrayList<DatabaseConnection> query(){
        return databaseConnectionDao.query();
    }
}

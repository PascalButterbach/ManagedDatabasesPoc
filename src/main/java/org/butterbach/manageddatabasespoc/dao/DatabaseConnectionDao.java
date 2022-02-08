package org.butterbach.manageddatabasespoc.dao;

import org.butterbach.manageddatabasespoc.model.DatabaseConnection;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.ArrayList;

@UseClasspathSqlLocator
public interface DatabaseConnectionDao {

    @SqlQuery
    ArrayList<DatabaseConnection> query();
}

package org.butterbach.manageddatabasespoc.dao;

import org.butterbach.manageddatabasespoc.model.User;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@UseClasspathSqlLocator
public interface UserDao {

    @SqlQuery
    User create(@BindBean("user") User user);

    @SqlQuery
    User retrieveRandomRow();

}

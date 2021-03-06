package org.butterbach.manageddatabasespoc.dao;

import org.butterbach.manageddatabasespoc.model.Customer;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@UseClasspathSqlLocator
public interface CustomerDao {

    @SqlQuery
    Customer create(@BindBean("customer") Customer customer);

    @SqlQuery
    Customer retrieveRandomRow();

}

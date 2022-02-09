package org.butterbach.manageddatabasespoc.service;

import org.butterbach.manageddatabasespoc.dao.CustomerDao;
import org.butterbach.manageddatabasespoc.model.Customer;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("tenant_jdbi") Jdbi jdbi) {
        this.customerDao = jdbi.onDemand(CustomerDao.class);
    }

    public Customer create(Customer customer){
        return customerDao.create(customer);
    }

    public Customer retrieveRandomRow(){
        return customerDao.retrieveRandomRow();
    }
}

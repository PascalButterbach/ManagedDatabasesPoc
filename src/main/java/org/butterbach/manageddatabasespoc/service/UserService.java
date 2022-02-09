package org.butterbach.manageddatabasespoc.service;

import org.butterbach.manageddatabasespoc.dao.UserDao;
import org.butterbach.manageddatabasespoc.model.User;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(@Qualifier("tenant_jdbi") Jdbi jdbi) {
        this.userDao = jdbi.onDemand(UserDao.class);
    }

    public User create(User user){
        return userDao.create(user);
    }

    public User retrieveRandomRow(){
        return userDao.retrieveRandomRow();
    }
}

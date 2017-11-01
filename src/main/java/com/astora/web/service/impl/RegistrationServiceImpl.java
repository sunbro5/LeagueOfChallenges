package com.astora.web.service.impl;

import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.User;
import com.astora.web.model.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 1.11.2017
 */
@Service("registrationService")
public class RegistrationServiceImpl {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void createUser(RegistrationModel registration){
        User user = new User();
        user.setNickname(registration.getNickname());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPassword(registration.getPassword());
        user.setEmail(registration.getEmail());
        userDao.create(user);
    }

}

package com.astora.web.service;

import com.astora.web.exception.ServiceException;
import com.astora.web.model.RegistrationModel;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 04.11.2017.
 */
public interface RegistrationService {

     void createUser(RegistrationModel registration) throws ServiceException;
}

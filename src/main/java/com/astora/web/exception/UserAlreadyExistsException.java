package com.astora.web.exception;

import javax.xml.ws.Service;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 04.11.2017.
 */
public class UserAlreadyExistsException extends ServiceException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

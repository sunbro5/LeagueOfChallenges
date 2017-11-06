package com.astora.web.exception;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
public class UserDoesntExists extends ServiceException {

    public UserDoesntExists(String message) {
        super(message);
    }

    public UserDoesntExists(String message, Throwable cause) {
        super(message, cause);
    }
}

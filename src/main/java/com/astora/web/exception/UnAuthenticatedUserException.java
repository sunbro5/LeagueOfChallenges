package com.astora.web.exception;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 12.2.2018
 */
public class UnAuthenticatedUserException extends ServiceException {

    public UnAuthenticatedUserException(String message) {
        super(message);
    }
}

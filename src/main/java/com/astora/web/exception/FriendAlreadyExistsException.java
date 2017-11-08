package com.astora.web.exception;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 08.11.2017
 */
public class FriendAlreadyExistsException extends ServiceException {
    public FriendAlreadyExistsException(String message) {
        super(message);
    }

    public FriendAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

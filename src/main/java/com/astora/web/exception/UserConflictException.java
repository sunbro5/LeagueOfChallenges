package com.astora.web.exception;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 12.2.2018
 */
public class UserConflictException extends ServiceException {

    private String nickname;

    public UserConflictException(String message) {
        super(message);
    }

    public UserConflictException(String message, String nickname) {
        super(message);
        this.nickname = nickname;
    }

    public UserConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}

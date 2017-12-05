package com.astora.web.exception;


/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 20.11.2017
 */
public class CantSendMessageException extends ServiceException{

    public CantSendMessageException(String message) {
        super(message);
    }
}

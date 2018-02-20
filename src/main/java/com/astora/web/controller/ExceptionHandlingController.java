package com.astora.web.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 12.2.2018
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = Logger.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception ex){
        logger.error("Request: " + request.getRequestURL() + " raised " + ex,ex);
        return "home";
    }
}

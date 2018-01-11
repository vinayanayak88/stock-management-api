package com.commerce.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.commerce.common.ErrorResponse;
import com.commerce.constant.Constant;


/**
 * Global exception handler class which returns meaningful
 * error responses in case if exception is thrown in internal
 * methods
 *
 * @author vinayanayak
 * @date 09-Jan-2018
 * GlobalExceptionHandler.java
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(ValidationException exception){
        logger.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getErrorMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse(Constant.UNEXPECTED_ERROR);
    }
    
    @ResponseBody
    @ExceptionHandler(value = OutdatedStockException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason="Outdated stock")
    public void handleException(OutdatedStockException exception){
       
    }
    
    @ResponseBody
    @ExceptionHandler(value = NoStockFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleException(NoStockFoundException exception){
    }
}

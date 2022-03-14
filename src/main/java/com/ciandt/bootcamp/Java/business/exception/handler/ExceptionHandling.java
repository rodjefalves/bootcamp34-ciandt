package com.ciandt.bootcamp.Java.business.exception.handler;

import com.ciandt.bootcamp.Java.business.exception.base.AlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ExceptionDetails;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    private final MessageSource messageSource;

    public ExceptionHandling(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(AlertException.class)
    public ResponseEntity<?> handleAlertException(AlertException alertException) {

        ExceptionDetails exceptionDetails = ExceptionDetails.ExceptionDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(alertException.getStatus().getStatusCode())
                .title(alertException.getStatus().getReasonPhrase())
                .message(messageSource.getMessage(alertException.getProblemKey().name(), alertException.getMessageArgs(),null))
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus
                .valueOf(alertException
                        .getStatus()
                        .getStatusCode()));
    }

}

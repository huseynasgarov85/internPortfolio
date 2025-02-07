package com.example.internportfoliotask2.globalException;

import com.example.internportfoliotask2.globalException.exceptions.*;
import com.example.internportfoliotask2.model.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExceptionDto handleAlreadyExistsException(AlreadyExistsException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(OtpInvalid.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleOtpInvalidException(OtpInvalid e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(UnexpectedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleUnexpectedException(UnexpectedException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }
    @ExceptionHandler(Bearer_Token.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleUnexpectedException(Bearer_Token e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleAuthenticatedException(AuthenticationException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

}

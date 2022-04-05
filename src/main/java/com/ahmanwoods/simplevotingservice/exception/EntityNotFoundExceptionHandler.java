package com.ahmanwoods.simplevotingservice.exception;

import com.ahmanwoods.simplevotingservice.apierror.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError handleEntityNotFoundException(EntityNotFoundException ex)
    {
        return new ApiError(
                HttpStatus.CONFLICT.value(),
                "The requested value was not found.");
    }
}


package com.ahmanwoods.simplevotingservice.exception;

import com.ahmanwoods.simplevotingservice.apierror.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceInUseExceptionHandler {
    @ExceptionHandler(ResourceInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiError handleUsernameInUseException(ResourceInUseException ex)
    {
        return new ApiError(
                HttpStatus.CONFLICT.value(),
                "The requested resource is already in use");
    }
}


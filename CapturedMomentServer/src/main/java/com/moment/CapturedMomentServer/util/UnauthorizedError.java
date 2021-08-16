package com.moment.CapturedMomentServer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedError extends IllegalArgumentException{
    public UnauthorizedError(String errorMsg){
        super(errorMsg);
    }
}

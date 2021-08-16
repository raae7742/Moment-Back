package com.moment.CapturedMomentServer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*custom exception 401 2021-08-16 김예진*/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedError extends IllegalArgumentException{
    public UnauthorizedError(String errorMsg){
        super(errorMsg);
    }
}

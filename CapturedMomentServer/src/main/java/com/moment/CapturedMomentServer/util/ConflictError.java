package com.moment.CapturedMomentServer.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*custom exception 409 2021-08-16 김예진*/
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictError extends RuntimeException{
    public ConflictError(String errorMsg){
        super(errorMsg);
    }
}

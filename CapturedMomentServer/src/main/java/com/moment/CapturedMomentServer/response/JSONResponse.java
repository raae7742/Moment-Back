package com.moment.CapturedMomentServer.response;

import io.swagger.annotations.ApiModelProperty;

public class JSONResponse<T> {

    @ApiModelProperty(example = "상태 코드")
    private int statusCode;

    @ApiModelProperty(example = "상태 메세지")
    private String message;

    @ApiModelProperty(example = "전달할 데이터")
    private T data;

    public JSONResponse() {
    }

    public JSONResponse(int code, String message, T data) {
        this.statusCode = code;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
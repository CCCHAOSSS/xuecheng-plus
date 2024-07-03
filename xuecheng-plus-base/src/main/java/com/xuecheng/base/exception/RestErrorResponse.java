package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @author limei
 * @data 2024/7/3
 * @description 异常类
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

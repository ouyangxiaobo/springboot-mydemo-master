package com.github.mydemo.common.exception;

import com.github.mydemo.common.resp.ResponseEnum;
import lombok.Getter;

/**
 * @Author swg.
 * @Date 2019/1/1 13:18
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
public class ParentException extends RuntimeException{
    private int exceptionStatus = ResponseEnum.ERROR.getCode();

    public ParentException(String msg){
        super(msg);
    }

    public ParentException(int code, String msg){
        super(msg);
        exceptionStatus = code;
    }

}

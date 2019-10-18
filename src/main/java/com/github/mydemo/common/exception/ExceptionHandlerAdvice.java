package com.github.mydemo.common.exception;



import com.github.mydemo.common.constants.Constants;
import com.github.mydemo.common.resp.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author swg.
 * @Date 2019/1/1 13:21
 * @CONTACT 317758022@qq.com
 * @DESC 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ServerResponse handleException(Exception e){
        log.error(e.getMessage(),e);
        return ServerResponse.createByErrorCodeMessage(Constants.RESP_STATUS_INTERNAL_ERROR,"系统异常，请稍后再试");
    }

    @ExceptionHandler(ParentException.class)
    public ServerResponse handleException(ParentException e){
        log.error(e.getMessage(),e);
        return ServerResponse.createByErrorCodeMessage(e.getExceptionStatus(),e.getMessage());
    }

}

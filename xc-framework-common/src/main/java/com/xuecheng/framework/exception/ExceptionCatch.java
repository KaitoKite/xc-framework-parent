package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice//控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //定义map，配置异常类型所对应的错误代码

    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    private static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder= ImmutableMap.builder();


    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException (CustomException customException){
        //记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        ResultCode resultCode = customException.resultCode;
        return new ResponseResult(resultCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exception (Exception exception){
        //记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
       if (EXCEPTIONS==null){
           EXCEPTIONS=builder.build();
       }
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
       if (resultCode!=null){
           return new ResponseResult(resultCode);
       }else {
           return new ResponseResult(CommonCode.SERVER_ERROR);
       }
    }




    static {
        //定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }
}

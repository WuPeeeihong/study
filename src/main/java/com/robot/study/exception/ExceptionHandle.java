package com.robot.study.exception;

import com.robot.study.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 统一异常处理类
 * @author: wph
 * @time: 2021/11/8
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 参数解析失败异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":参数解析失败", e);
        return Result.error(new MyException(HttpStatus.BAD_REQUEST.value(),"参数解析失败"));
    }

    /**
     * 不支持当前请求方法异常处理
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":不支持当前请求方法",e);
        return Result.error(new MyException(HttpStatus.METHOD_NOT_ALLOWED.value(),"不支持当前请求方法"));
    }

    /**
     * 项目运行异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":服务运行异常",e);
        return Result.error(new MyException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务运行异常"));

    }

    /**
     * 自定义异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handleException(MyException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":自定义内部异常======" + e.getMessage());
        return Result.error(e);
    }

}

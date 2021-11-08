package com.robot.study.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author Wuph
 * @Date: create in 2021/11/05/ 15:42
 * @Description
 */
@Aspect
@Component
public class WebLogConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 切入点
     */
    @Pointcut("execution(* com.robot.study.controller.*.*(..))")
    public void log() {

    }

    /**
     * 方法请求前
     */
    @Before("log()")
    public void doBefore() {

    }

    /**
     * 方法请求后
     * 说明：用户信息打印
     * @param joinPoint 连接点
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestMethod requestLog = new RequestMethod(url, ip, classMethod, args);
        log.info("Request : {}", requestLog);
    }

    /**
     * 返回执行结果，在执行方法后返回
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        log.info("Result : {}", result);
    }

    private class RequestMethod {
        private String url;         // 请求 url
        private String ip;          // 请求 ip
        private String classMethod; // 请求方法
        private Object[] args;      // 请求参数

        public RequestMethod(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestMethod{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}

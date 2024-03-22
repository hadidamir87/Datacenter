package com.example.eshragh.aop.exception.log;

import com.example.eshragh.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
@Slf4j
public class ControllerAspect {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
   private LogRepository logRepository;

    @Around("within(com.example.eshragh.rest.AbstractController+ )")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LogModel logModel = new LogModel();
//        joinPoint.proceed();
        logModel.setMethodName(joinPoint.getSignature().getName());
        logModel.setRequest(joinPoint.getArgs());
        Object value ;

//        logModel.setResponse(value);
//        logRepository.save(logModel);
//        System.out.println(joinPoint.proceed());
//        System.out.println(logModel);
        try {
            value = joinPoint.proceed();
            if (value != null) {
                logModel.setResponse(value);
//                System.out.println(logModel);
            }
            logRepository.save(logModel);
            log.info("Success req " + objectMapper.writeValueAsString(logModel));
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            writer.close();
            printWriter.close();
            logModel.setErrorTrace(writer.toString());
            logRepository.save(logModel);
            log.error("Failure req " + objectMapper.writeValueAsString(logModel));
            throw e;
        }
//        return value;
        return value;
    }



/*@Before("within(com.example.eshragh.rest+ )")
    public void before() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            log.info("request coming from " + request.getServerName() + " AND iP is => " + request.getLocalAddr() + " AND CALL URL =>  " + request.getRequestURI());
        }


    }*/


/*
@Around("within(com.example.eshragh.rest+ )")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        LogModel logModel = new LogModel();
        logModel.setMethodName(joinPoint.getSignature().getName());

        logModel.setRequest(joinPoint.getArgs());

        Object value;
        try {
            value = joinPoint.proceed();
            if (value != null) {
                logModel.setResponse(value);
            }
//            logRepository.save(logModel);
//            log.info("Success req " + objectMapper.writeValueAsString(logModel));


        } catch (Throwable e) {


            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            writer.close();
            printWriter.close();
            logModel.setErrorTrace(writer.toString());
            logRepository.save(logModel);
            log.error("Failure req " + objectMapper.writeValueAsString(logModel));
            throw e;

        }


        return value;
    }*/


}

package com.bs.boot.boottest.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class MyAspect {
//    @Before("execution(* com.bs.boot.boottest.common.websocket..afterConnection*(..))")
//    public void beforeLog(JoinPoint joinPoint) {
//        log.debug("==== Aop 적용하기 ====");
//        Signature signature = joinPoint.getSignature();
//        log.debug(signature.getDeclaringTypeName() + "." + signature.getName());
//
//    }

    @Around("execution(* com.bs.boot..*.*(..))")
    public Object testAfterLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.debug("{} 메소드 실행", methodName);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        log.debug("{} 메소드 실행시간 {}ms", methodName, stopWatch.getTotalTimeMillis());
        return result;
    }
}

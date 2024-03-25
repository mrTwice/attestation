package ru.yampolskiy.taskclient.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllersLoggingAspect {

    private Logger logger = LoggerFactory.getLogger(ControllersLoggingAspect.class);

    @Before("execution(* ru.yampolskiy.taskclient.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод {} в классе {} вызван с аргументами: {}", methodName, className, args);
    }

    @AfterReturning(pointcut = "execution(* ru.yampolskiy.taskclient.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("Метод {} в классе {} выполнен с результатом: {}", methodName, className, result);
    }

    @AfterThrowing(pointcut = "execution(* ru.yampolskiy.taskclient.controller.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.error("Метод {} в классе {} выбросил исключение: {}", methodName, className, exception.getMessage());
    }
}

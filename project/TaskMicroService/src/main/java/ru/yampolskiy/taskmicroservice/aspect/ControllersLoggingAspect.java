package ru.yampolskiy.taskmicroservice.aspect;

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

    /**
     * Логирует информацию перед выполнением метода контроллера.
     * @param joinPoint Точка соединения, представляющая вызов метода контроллера.
     */
    @Before("execution(* ru.yampolskiy.taskmicroservice.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод {} класса {} вызван с аргументами: {}", methodName, className, args);
    }

    /**
     * Логирует информацию после успешного выполнения метода контроллера.
     * @param joinPoint Точка соединения, представляющая вызов метода контроллера.
     * @param result Результат выполнения метода контроллера.
     */
    @AfterReturning(pointcut = "execution(* ru.yampolskiy.taskmicroservice.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("Метод {} класса {} вернул результат: {}", methodName, className, result);
    }

    /**
     * Логирует информацию об исключении, выброшенном в методе контроллера.
     * @param joinPoint Точка соединения, представляющая вызов метода контроллера.
     * @param exception Исключение, выброшенное методом контроллера.
     */
    @AfterThrowing(pointcut = "execution(* ru.yampolskiy.taskmicroservice.controller.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.error("Метод {} класса {} выбросил исключение: {}", methodName, className, exception.getMessage());
    }
}

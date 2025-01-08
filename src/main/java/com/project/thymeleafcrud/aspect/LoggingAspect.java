package com.project.thymeleafcrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    // This will help us to log the data

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut expression
    // call when any method of controller package will be called
    @Pointcut("execution(* com.project.thymeleafcrud.controller.*.*(..))")
    private void forControllerPackage() {
    }

    // For any method of service package will be called
    @Pointcut("execution(* com.project.thymeleafcrud.service.*.*(..))")
    private void forServicePackage() {
    }

    // For any method of dao package will be called
    @Pointcut("execution(* com.project.thymeleafcrud.dao.*.*(..))")
    private void forDAOPackage() {
    }

    // Combining everything
    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {
    }


    // Adding @Before Advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("===> In @Before : Calling Method : " + theMethod);

        // display the arguments to the method
        Object[] args = joinPoint.getArgs();
        for (Object object : args) {
            logger.info("===> Argument : " + object);
        }
    }

    // Adding the code after returning the advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterReturning(JoinPoint joinPoint, Object theResult) {
        // Display the method we are returning from
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("===> In @AfterReturning : Calling Method : " + theMethod);

        // Display the data we are returning
        logger.info("===> Returning data : " + theResult);
    }
}









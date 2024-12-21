package edu.miu.cs.cs544.lotu.springboot.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* edu.miu.cs.cs544.lotu.springboot.project.service..*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        LOGGER.log(Level.INFO, "Entering method: {0} with arguments: {1}",
                new Object[]{joinPoint.getSignature(), java.util.Arrays.toString(joinPoint.getArgs())});
    }

    @After("execution(* edu.miu.cs.cs544.lotu.springboot.project.service..*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        LOGGER.log(Level.INFO, "Exiting method: {0}", joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "execution(* edu.miu.cs.cs544.lotu.springboot.project.service..*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Exception exception) {
        LOGGER.log(Level.SEVERE, "Exception in method: {0} with arguments: {1}. Exception: {2}",
                new Object[]{joinPoint.getSignature(), java.util.Arrays.toString(joinPoint.getArgs()), exception.getMessage()});
    }
}

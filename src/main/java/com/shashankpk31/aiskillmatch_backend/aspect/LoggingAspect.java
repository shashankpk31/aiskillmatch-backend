
package com.shashankpk31.aiskillmatch_backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Service *)")
    public void joinPoint() {
    }

    @Around("joinPoint()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.debug("ENTERING {} of {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
        Object result = joinPoint.proceed();
        LOG.debug("EXITING {} of {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
        return result;
    }

    @AfterThrowing(pointcut = "joinPoint()", throwing = "e")
    public void logAfterThrowing(ProceedingJoinPoint joinPoint, Throwable e) {
        LOG.error("EXCEPTION in {} of {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName(), e);
    }
    
}

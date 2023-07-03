package by.tkach.news.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* by.tkach.news.controllers.*Controller.*(..))")
    public void controllers() {}

    @Pointcut("execution(* by.tkach.news.services.impl.*Imply.*(..))")
    public void services() {}

    @Pointcut("execution(* by.tkach.news.services.facade.*Facade.*(..))")
    public void facades() {}

    @Pointcut("execution(* by.tkach.news.repositories.*Repository.*(..))")
    public void repositories() {}

    @Before("controllers() || services() || facades() || repositories()")
    public void beforeServicesAndRepositories(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        LocalDateTime time = LocalDateTime.now().withNano(0);

        log.debug("[{}]. Called method '{}.{}' with arguments: {}", time, className, methodName, args);
    }

    @AfterReturning(value = "controllers() || services() || facades() || repositories()", returning = "retVal")
    public void afterServicesAndRepositories(JoinPoint joinPoint, Object retVal) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        LocalDateTime time = LocalDateTime.now().withNano(0);

        log.debug("[{}]. Returned value from method '{}.{}' : {}", time, className, methodName, retVal);
    }
}

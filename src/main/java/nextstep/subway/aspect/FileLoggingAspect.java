package nextstep.subway.aspect;

import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FileLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger("file");

    @Around("execution(* nextstep.subway..ui.*Controller.*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        MDC.put("requestUUID", UUID.randomUUID().toString());
        final String requestUUID = MDC.get("requestUUID");

        log.info(">>> [{}] REQUEST = [{}], method = [{}], param = [{}]", requestUUID, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
        final Object result = joinPoint.proceed();
        log.info(">>> [{}] RESPONSE = [{}]", requestUUID, result.toString());
        return result;
    }

}

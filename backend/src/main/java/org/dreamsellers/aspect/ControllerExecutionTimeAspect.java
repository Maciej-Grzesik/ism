package org.dreamsellers.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class ControllerExecutionTimeAspect {

    private final ConcurrentHashMap<String, Long> executionTimes = new ConcurrentHashMap<>();

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void anyRestControllerMethod() {}

    @Around("anyRestControllerMethod()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long start = System.nanoTime();

        Object result = joinPoint.proceed();
        long duration = System.nanoTime() - start;
        executionTimes.merge(methodName, duration, Math::max);
        return result;
    }

    public ConcurrentHashMap<String, Long> getExecutionTimes() {
        return executionTimes;
    }

    public String getSlowestEndpoint() {
        return executionTimes.entrySet()
                .stream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .map(e -> e.getKey() + " - " + e.getValue() / 1_000_000 + " ms")
                .orElse("No invocations yet");
    }
}

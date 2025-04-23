package org.dreamsellers.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class ControllerInvocationAspect {

    private final ConcurrentHashMap<String, AtomicInteger> controllerInvocationCount = new ConcurrentHashMap<>();

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void anyRestController() {}

    @Before("anyRestController()")
    public void countControllerInvocation(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        controllerInvocationCount
                .computeIfAbsent(methodName, k -> new AtomicInteger(0))
                .incrementAndGet();
    }

    public ConcurrentHashMap<String, AtomicInteger> getInvocationCounts() {
        return controllerInvocationCount;
    }

    public String getMostInvokedControllerMethod() {
        return controllerInvocationCount.entrySet()
                .stream()
                .max((e1, e2) -> Integer.compare(e1.getValue().get(), e2.getValue().get()))
                .map(e -> e.getKey() + " - " + e.getValue())
                .orElse("No invocations yet");
    }
}

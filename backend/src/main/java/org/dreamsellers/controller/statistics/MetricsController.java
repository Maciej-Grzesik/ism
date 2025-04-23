package org.dreamsellers.controller.statistics;

import org.dreamsellers.aspect.ControllerInvocationAspect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final ControllerInvocationAspect controllerInvocationAspect;

    public MetricsController(ControllerInvocationAspect controllerInvocationAspect) {
        this.controllerInvocationAspect = controllerInvocationAspect;
    }

    @GetMapping("/invocations")
    public Map<String, Integer> getAllInvocationCounts() {
        return controllerInvocationAspect.getInvocationCounts()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().get()
                ));
    }

    @GetMapping("/top")
    public String getMostInvokedMethod() {
        return controllerInvocationAspect.getMostInvokedControllerMethod();
    }
}


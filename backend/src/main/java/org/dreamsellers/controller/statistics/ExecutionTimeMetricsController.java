package org.dreamsellers.controller.statistics;
import org.dreamsellers.aspect.ControllerExecutionTimeAspect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class ExecutionTimeMetricsController {

    private final ControllerExecutionTimeAspect controllerExecutionTimeAspect;

    public ExecutionTimeMetricsController(ControllerExecutionTimeAspect controllerExecutionTimeAspect) {
        this.controllerExecutionTimeAspect = controllerExecutionTimeAspect;
    }

    @GetMapping("/execution-times")
    public Map<String, Long> getAllExecutionTimes() {
        return controllerExecutionTimeAspect.getExecutionTimes()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() / 1_000_000
                ));
    }

    @GetMapping("/slowest")
    public String getSlowestEndpoint() {
        return controllerExecutionTimeAspect.getSlowestEndpoint();
    }
}

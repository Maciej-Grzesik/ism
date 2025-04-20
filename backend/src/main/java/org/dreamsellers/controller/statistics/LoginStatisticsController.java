package org.dreamsellers.controller.statistics;

import lombok.RequiredArgsConstructor;
import org.dreamsellers.aspect.LoginStatisticsAspect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class LoginStatisticsController {

    private final LoginStatisticsAspect loginStatisticsAspect;

    @GetMapping("/logins")
    public int getLoginCount() {
        return loginStatisticsAspect.getLoginCount();
    }
}

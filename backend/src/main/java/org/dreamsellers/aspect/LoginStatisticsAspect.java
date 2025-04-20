package org.dreamsellers.aspect;

import lombok.Getter;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

@Getter
@Aspect
@Component
public class LoginStatisticsAspect {

    private int loginCount = 0;

    @AfterReturning("execution(public org.model.AuthenticatedUserDto org.dreamsellers.service.AuthService.login(..))")
    public void countLogin() {
        loginCount++;
        System.out.println("Login count: " + loginCount);
    }

}

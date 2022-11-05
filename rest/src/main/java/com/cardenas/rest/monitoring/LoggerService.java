package com.cardenas.rest.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements HealthIndicator {

    private final String LOGGER_SERVICE ="Logger SErvice";
    @Override
    public Health health() {
        if(isLoggerServiceGood()){
            return Health.up().withDetail(LOGGER_SERVICE,"Service is running").build();
        }
        return Health.down().withDetail(LOGGER_SERVICE,"Service is not avaliable").build();
    }

    private boolean isLoggerServiceGood(){
        return true;
    }
}
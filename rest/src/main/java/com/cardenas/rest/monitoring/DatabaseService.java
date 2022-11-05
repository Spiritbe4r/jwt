package com.cardenas.rest.monitoring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseService implements HealthIndicator {
    @Autowired
    JdbcTemplate template;
    private final String DATABASE_SERVICE="DbSErvice";
    @Override
    public Health health() {
        if(isDatabaseHealthGood()){
            return Health.up().withDetail(DATABASE_SERVICE,"Service is running").build();
        }
        return Health.down().withDetail(DATABASE_SERVICE,"Service is running").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }

    public int check(){
        List<Object> results = template.query("select 1 from dual",
                new SingleColumnRowMapper<>());
        return results.size();
    }
}

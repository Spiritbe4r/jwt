package com.cardenas.rest.security.filters;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class TimeAccessFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LogManager.getLogger(this.getClass().getName()).debug(">>> Time access per filter");

        int hour= LocalDateTime.now().getHour();
        if(4<= hour && hour < 4){
            filterChain.doFilter(request,response);
        }
        else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

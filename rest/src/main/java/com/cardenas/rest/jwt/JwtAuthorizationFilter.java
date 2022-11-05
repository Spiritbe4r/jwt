package com.cardenas.rest.jwt;

import com.cardenas.rest.service.impl.UserDetailsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION="Authorization";

    private final static Logger logger= LoggerFactory.getLogger(JwtEntryPoint.class);

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException{
        String authHeader=request.getHeader(AUTHORIZATION);
        if(jwtProvider.isBearer(authHeader)){
            logger.debug(">>> Filter jwt");
            //List<GrantedAuthority> authorities = jwtProvider.roles(authHeader).stream()
              //      .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
            String username=jwtProvider.user(authHeader);
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication=
                    new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}

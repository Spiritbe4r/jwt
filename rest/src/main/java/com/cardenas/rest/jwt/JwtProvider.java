package com.cardenas.rest.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cardenas.rest.entity.Role;
import com.cardenas.rest.entity.UserPrincipal;
import com.cardenas.rest.exceptions.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    private final static Logger logger= LoggerFactory.getLogger(JwtProvider.class);

    private static final String BEARER="Bearer ";

    private static final String USER="user";
    private static final String ROLES="roles";
    private static final String ISSUER="spring-app";
    private static final int EXPIRES_IN_MILLISECOND=3600000;
    private static final String SECRET="zZrq0sZK1yt9RJk51RTJ/jeU6WERbvr8nqKMWQJRX1E=";


    public String generateToken(Authentication authenticaton){
        UserPrincipal userPrincipal= (UserPrincipal) authenticaton.getPrincipal();

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_IN_MILLISECOND))
                .withClaim(USER, userPrincipal.getUsername())
                             //.withArrayClaim(ROLES, roles.toArray(new String[0]))
                .sign(Algorithm.HMAC256(SECRET));
        
    }



    public boolean isBearer(String authorization) {
        return authorization != null && authorization.startsWith(BEARER) && authorization.split("\\.").length == 3;
    }

    public String user(String authorization) throws JwtException {
        return this.verify(authorization).getClaim(USER).asString();
    }

    private DecodedJWT verify(String authorization) throws JwtException {
        if (!this.isBearer(authorization)) {
            throw new JwtException("It is not Berear");
        }
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception exception) {
            throw new JwtException("JWT is wrong. " + exception.getMessage());
        }
    }

    public List<String> roles(String authorization) throws JwtException {
        return Arrays.asList(this.verify(authorization).getClaim(ROLES).asArray(String.class));
    }

}

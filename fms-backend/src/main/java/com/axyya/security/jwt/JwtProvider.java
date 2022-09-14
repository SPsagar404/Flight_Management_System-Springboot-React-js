package com.axyya.security.jwt;

import org.springframework.security.core.Authentication;

import com.axyya.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sa
 * @date 10.10.2021
 * @time 12:05
 */
public interface JwtProvider
{
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}

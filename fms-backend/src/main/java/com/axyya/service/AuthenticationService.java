package com.axyya.service;

import com.axyya.model.User;

/**
 * @author sa
 * @date 10.10.2021
 * @time 12:31
 */
public interface AuthenticationService
{
    User signInAndReturnJWT(User signInRequest);
}

package com.api.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000;
    public static final Key JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}

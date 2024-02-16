package com.api.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class TokenBlackList {
    private Set<String> invalidatedTokens = new HashSet<>();

    public void addToBlacklist(String token) {
        invalidatedTokens.add(token);
    }

    public boolean isTokenInvalid(String token) {
        return invalidatedTokens.contains(token);
    }

}

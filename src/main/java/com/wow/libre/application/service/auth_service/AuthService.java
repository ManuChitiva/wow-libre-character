package com.wow.libre.application.service.auth_service;

import com.wow.libre.domain.exception.UnauthorizedException;
import com.wow.libre.domain.ports.in.auth.AuthPort;
import com.wow.libre.infrastructure.client.AuthClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthPort {

    private final AuthClient authClient;

    public AuthService(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    public void verifyAccount(long accountId, long accountWebId, String transactionId) {
        if (!authClient.verifyAccount(accountId, accountWebId, transactionId)) {
            throw new UnauthorizedException("Account is invalid.", transactionId);
        }
    }
}

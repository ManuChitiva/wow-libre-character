package com.wow.libre.domain.ports.in.auth;

public interface AuthPort {

    void verifyAccount(long accountId, long accountWebId, String transactionId);
}

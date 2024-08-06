package com.wow.libre.infrastructure.client;

import com.wow.libre.domain.shared.GenericResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;


@Component
public class AuthClient {
    private final RestTemplate restTemplate;

    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyAccount(Long accountId, Long accountWebId, String transactionId) {
        HttpHeaders headers = new HttpHeaders();

        headers.set(HEADER_TRANSACTION_ID, transactionId);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GenericResponse<Boolean>> response = restTemplate.exchange(String.format("http://localhost" +
                            ":8080/api/account/verify?account_id=%d&account_web_id=%d", accountId, accountWebId),
                    HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {
                    });

            if (response.getStatusCode().is2xxSuccessful()) {
                return Objects.requireNonNull(response.getBody()).getData();
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("HTTP error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }
}

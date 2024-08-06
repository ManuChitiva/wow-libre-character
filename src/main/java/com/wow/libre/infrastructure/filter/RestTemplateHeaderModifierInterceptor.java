package com.wow.libre.infrastructure.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestTemplateHeaderModifierInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOGGER.info(
                "===========================Request begin======================================");
        LOGGER.info("URI         : {}", request.getURI());
        LOGGER.info("Method      : {}", request.getMethod());
        LOGGER.info("Headers     : {}", request.getHeaders());
        LOGGER.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        LOGGER.info(
                "==========================Request end=========================================");
        return execution.execute(request, body);
    }
}

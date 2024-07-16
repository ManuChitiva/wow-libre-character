package com.wow.libre.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Base64;

@Configuration
public class WebServiceMessageSenderWithAuth extends HttpUrlConnectionMessageSender {

    @Value("${application.credentials.command.username}")
    private String username;
    @Value("${application.credentials.command.password}")
    private String password;

    @Override
    protected void prepareConnection(HttpURLConnection connection)
            throws IOException {

        Base64.Encoder enc = Base64.getEncoder();
        String credentials = String.format("%s:%s", username, password);
        String encodedAuthorization = enc.encodeToString(credentials.getBytes());
        connection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthorization);

        super.prepareConnection(connection);
    }
}

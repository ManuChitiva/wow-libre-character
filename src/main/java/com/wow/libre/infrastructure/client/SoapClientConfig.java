package com.wow.libre.infrastructure.client;

import com.wow.libre.infrastructure.client.soap.xml.CustomLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class SoapClientConfig {
    @Bean
    public Jaxb2Marshaller requestMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.wow.libre.infrastructure.client.soap.xml"); // Paquete para las clases de
        // solicitud
        return marshaller;
    }

    @Bean
    public Jaxb2Marshaller responseUnmarshaller() {
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setContextPath("com.wow.libre.infrastructure.client.soap.xml.resp"); // Paquete para las clases
        // de respuesta
        return unmarshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller requestMarshaller,
                                                 Jaxb2Marshaller responseUnmarshaller,
                                                 WebServiceMessageSenderWithAuth auth) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(requestMarshaller);
        webServiceTemplate.setUnmarshaller(responseUnmarshaller);
        webServiceTemplate.setMessageSender(auth);
        webServiceTemplate.setInterceptors(new ClientInterceptor[]{new CustomLoggingInterceptor()});

        webServiceTemplate.setDefaultUri("http://127.0.0.1:7878"); // Cambia esto a tu URI de servicio real
        return webServiceTemplate;
    }
}

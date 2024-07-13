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
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.wow.libre.infrastructure.client.soap.xml"); // Asegúrate de que sea el paquete correcto
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setMessageSender(new WebServiceMessageSenderWithAuth());
        webServiceTemplate.setInterceptors(new ClientInterceptor[] { new CustomLoggingInterceptor() });

        webServiceTemplate.setDefaultUri("http://127.0.0.1:7878"); // Cambia esto a tu URI de servicio real
        return webServiceTemplate;
    }
}

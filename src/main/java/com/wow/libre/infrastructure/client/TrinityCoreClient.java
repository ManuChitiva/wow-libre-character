package com.wow.libre.infrastructure.client;


import com.wow.libre.infrastructure.client.soap.xml.Envelope;
import com.wow.libre.infrastructure.client.soap.xml.Envelope2;
import com.wow.libre.infrastructure.client.soap.xml.ExecuteCommand2;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.io.StringWriter;

@Component
public class TrinityCoreClient extends WebServiceGatewaySupport {

    private final WebServiceTemplate webServiceTemplate;

    public TrinityCoreClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Envelope executeCommand(String command) {
        Envelope2 requestEnvelope = new Envelope2();
        ExecuteCommand2 executeCommand = new ExecuteCommand2();
        executeCommand.setCommand(command);
        Envelope2.Body2 body = new Envelope2.Body2();
        body.setExecuteCommand(executeCommand);
        requestEnvelope.setBody(body);

        // Agregar logging para verificar la solicitud antes de enviarla
        StringWriter sw = new StringWriter();
        try {
            JAXBContext.newInstance(Envelope2.class).createMarshaller().marshal(requestEnvelope, sw);
            System.out.println("Request XML: " + sw.toString());

            // Enviar solicitud y recibir respuesta
            Envelope2 responseEnvelope = (Envelope2) webServiceTemplate.marshalSendAndReceive(requestEnvelope);
            return responseEnvelope;

        } catch (SoapFaultClientException e) {
            // Manejar la excepción de SOAP
            e.printStackTrace();
            throw new RuntimeException("SOAP Fault: " + e.getMessage(), e);

        } catch (JAXBException e) {
            // Manejar excepciones de JAXB
            throw new RuntimeException("JAXB Exception: " + e.getMessage(), e);
        }
    }


}

package com.wow.libre.infrastructure.client;

import com.wow.libre.infrastructure.client.soap.xml.ExecuteCommand;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class TrinityCoreClient extends WebServiceGatewaySupport {

    private final WebServiceTemplate webServiceTemplate;

    public TrinityCoreClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void executeCommand(String command) throws JAXBException {
        ExecuteCommand executeCommand = new ExecuteCommand();
        executeCommand.setCommand(command);
        webServiceTemplate.marshalSendAndReceive(executeCommand);
    }
}

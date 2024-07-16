package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public Envelope createEnvelope() {
        return new Envelope();
    }

    public Envelope.Body createEnvelopeBody() {
        return new Envelope.Body();
    }

    public ExecuteCommandResponse createExecuteCommandResponse() {
        return new ExecuteCommandResponse();
    }
}

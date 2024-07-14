package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public com.wow.libre.infrastructure.client.soap.xml.resp.Envelope createEnvelope() {
        return new com.wow.libre.infrastructure.client.soap.xml.resp.Envelope();
    }

    public com.wow.libre.infrastructure.client.soap.xml.resp.Envelope.Body createEnvelopeBody() {
        return new Envelope.Body();
    }

    public com.wow.libre.infrastructure.client.soap.xml.resp.ExecuteCommandResponse createExecuteCommandResponse() {
        return new ExecuteCommandResponse();
    }
}

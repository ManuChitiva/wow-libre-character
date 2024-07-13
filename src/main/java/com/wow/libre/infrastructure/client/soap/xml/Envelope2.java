package com.wow.libre.infrastructure.client.soap.xml;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "body"
})
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class Envelope2 extends Envelope {

    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/", required = true)
    protected Envelope2.Body2 body;

    public Envelope2.Body2 getBody() {
        return body;
    }

    public void setBody(Envelope2.Body2 value) {
        this.body = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "executeCommand"
    })
    public static class Body2 extends Body {

        @XmlElement(name = "executeCommand", namespace = "urn:TC", required = true)
        protected ExecuteCommand executeCommand;

        public ExecuteCommand getExecuteCommand() {
            return executeCommand;
        }

        public void setExecuteCommand(ExecuteCommand value) {
            this.executeCommand = value;
        }
    }
}
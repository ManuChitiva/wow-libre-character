package com.wow.libre.infrastructure.client.soap.xml.response;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"body"}
)
@XmlRootElement(
        name = "Envelope"
)
public class Envelope {
    @XmlElement(
            name = "Body",
            required = true
    )
    protected com.wow.libre.infrastructure.client.soap.xml.response.Envelope.Body body;

    public Envelope() {
    }

    public com.wow.libre.infrastructure.client.soap.xml.response.Envelope.Body getBody() {
        return this.body;
    }

    public void setBody(com.wow.libre.infrastructure.client.soap.xml.response.Envelope.Body value) {
        this.body = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
            name = "",
            propOrder = {"executeCommandResponse"}
    )
    public static class Body {
        @XmlElement(
                required = true
        )
        protected com.wow.libre.infrastructure.client.soap.xml.response.ExecuteCommandResponse executeCommandResponse;

        public Body() {
        }

        public com.wow.libre.infrastructure.client.soap.xml.response.ExecuteCommandResponse getExecuteCommandResponse() {
            return this.executeCommandResponse;
        }

        public void setExecuteCommandResponse(ExecuteCommandResponse value) {
            this.executeCommandResponse = value;
        }
    }
}
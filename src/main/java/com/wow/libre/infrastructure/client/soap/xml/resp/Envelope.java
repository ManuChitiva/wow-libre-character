package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"body"}
)
@XmlRootElement(
        name = "Envelope" ,namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
public class Envelope {
    @XmlElement(
            name = "Body",
            required = true
    )
    protected Body body;

    public Envelope() {
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body value) {
        this.body = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
            name = "",
            propOrder = {"executeCommandResponse"}
    )
    public static class Body {
        @XmlElement(
                required = true, namespace = "urn:TC"
        )
        protected ExecuteCommandResponse executeCommandResponse;

        public Body() {
        }

        public ExecuteCommandResponse getExecuteCommandResponse() {
            return this.executeCommandResponse;
        }

        public void setExecuteCommandResponse(ExecuteCommandResponse value) {
            this.executeCommandResponse = value;
        }
    }
}
package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"body"}
)
@XmlRootElement(
        name = "Envelope" ,namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
@Data
public class Envelope {
    @XmlElement(
            name = "Body",
            required = true
    )
    protected Body body;

    public Envelope() {
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
            name = "",
            propOrder = {"executeCommandResponse"}
    )
    @Data
    public static class Body {
        @XmlElement(
                required = true, namespace = "urn:TC"
        )
        protected ExecuteCommandResponse executeCommandResponse;

        public Body() {
        }
    }
}
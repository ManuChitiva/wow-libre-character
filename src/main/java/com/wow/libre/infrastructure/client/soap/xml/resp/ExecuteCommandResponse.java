package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"result"}
)
@XmlRootElement(
        name = "executeCommandResponse", namespace = "urn:TC"
)
@Data
public class ExecuteCommandResponse {
    @XmlElement(
            required = true
    )
    protected String result;

    public ExecuteCommandResponse() {
    }
}

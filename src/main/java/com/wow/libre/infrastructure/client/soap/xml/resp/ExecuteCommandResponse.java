package com.wow.libre.infrastructure.client.soap.xml.resp;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"result"}
)
@XmlRootElement(
        name = "executeCommandResponse", namespace = "urn:TC"
)
public class ExecuteCommandResponse {
    @XmlElement(
            required = true
    )
    protected String result;

    public ExecuteCommandResponse() {
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String value) {
        this.result = value;
    }
}

package com.wow.libre.infrastructure.client.soap.xml.response;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeCommandResponse", namespace = "urn:TC")
public class ExecuteCommandResponse implements Serializable {
    @XmlElement(name = "result", namespace = "urn:TC")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

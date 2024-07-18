package com.wow.libre.infrastructure.client.soap.xml;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "executeCommand", namespace = "urn:TC")
public class ExecuteCommand {


    protected String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String value) {
        this.command = value;
    }
}

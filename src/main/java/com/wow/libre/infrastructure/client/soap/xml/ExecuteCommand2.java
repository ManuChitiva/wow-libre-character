package com.wow.libre.infrastructure.client.soap.xml;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "command"
})
@XmlRootElement(name = "executeCommand", namespace = "urn:TC")
public class ExecuteCommand2 extends ExecuteCommand {

    @XmlElement(namespace = "urn:TC", required = true)
    protected String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String value) {
        this.command = value;
    }
}

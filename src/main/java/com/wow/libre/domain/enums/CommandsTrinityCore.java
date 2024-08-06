package com.wow.libre.domain.enums;

import com.wow.libre.domain.model.ItemQuantityModel;

import java.util.List;

public class CommandsTrinityCore {
    public static String invite(String playerName, String guildName) {
        return String.format(".guild invite %s \"%s\"", playerName, guildName);
    }

    public static String sendItems(String playerName, String subject, String body, List<ItemQuantityModel> items) {
        StringBuilder commandBuilder = new StringBuilder(".send items ");
        commandBuilder.append(playerName).append(" \"").append(subject).append("\" \"").append(body).append("\" ");

        for (ItemQuantityModel item : items) {
            commandBuilder.append(item.id).append(":").append(item.quantity).append(" ");
        }

        return commandBuilder.toString().trim();
    }

    public static String sendMail(String playerName, String subject, String body) {
        return String.format(".send mail %s %s %s", playerName, subject, body);
    }
}

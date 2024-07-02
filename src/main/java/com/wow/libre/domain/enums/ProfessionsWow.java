package com.wow.libre.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProfessionsWow {
    /* VERSION: 3.3.5 SUPPORT */
    ALCHEMY(171, "alchemy"),
    BLACKSMITH(164, "blacksmith"),
    ENCHANTMENT(333, "enchantment"),
    ENGINEERING(202, "engineering"),
    HERBALISM(182, "herbalism"),
    INSCRIPTION(773, "inscription"),
    JEWELCRAFTING(755, "jewelcrafting"),
    LEATHERWORKING(165 , "leatherworking"),
    MINING(186 , "mining"),
    SKINNING(393  , "skinning"),
    TAILORING(197  , "tailoring"),

    DEFAULT(0, "");

    private final int id;
    private final String description;

    ProfessionsWow(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ProfessionsWow getById(int id) {
        return Arrays.stream(values())
                .filter(race -> race.getId() == id)
                .findFirst()
                .orElse(DEFAULT);
    }
}

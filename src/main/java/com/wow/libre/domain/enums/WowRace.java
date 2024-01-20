package com.wow.libre.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WowRace {
  HUMAN(1, "HUMANO", "Alliance"),
  ORC(2, "ORCO", "Horde"),
  DWARF(3, "ENANO", "Alliance"),
  NIGHT_ELF(4, "ELFO DE LA NOCHE", "Alliance"),
  UNDEAD(5, "NO MUERTO", "Horde"),
  TAUREN(6, "TAUREN", "Horde"),
  GNOME(7, "GNOME", "Alliance"),
  TROLL(8, "TROLL", "Horde"),
  GOBLIN(9, "GOBLIN", "Horde"),
  BLOOD_ELF(10, "ELFO DE SANGRE", "Horde"),
  DRAENEI(11, "DRAENI", "Alliance"),
  WORGEN(22, "WORGEN", "Alliance"),
  PANDAREN_ALLIANCE(24, "PANDARIA", "Alliance"),
  PANDAREN_HORDE(25, "PANDARIA", "Horde"),
  VOID_ELF(29, "Los Elfos del Vacío", "Alliance"),
  LIGHTFORGED_DRAENEI(30, " Los Draenei Forjados por la Luz", "Horde"),
  HIGHMOUNTAIN_TAUREN(28, "Tauren Monte Alto", "Horde"),
  NIGHTBORNE(27, "Nocheterna", "Alliance"),
  DARK_IRON_DWARF(34, "Enano Hierro Negro", "Alliance"),
  VULPERA(35, "Vulpera", "Horde"),
  MAGHAR_ORC(36, "Orco Mag'har", "Horde"),
  MECHAGNOME(37, "Mecagnomo", "Alliance"),
  DEFAULT(0, "", "");

  private final int id;
  private final String raceName;
  private final String faction;

  WowRace(int id, String raceName, String faction) {
    this.id = id;
    this.raceName = raceName;
    this.faction = faction;
  }

  public static WowRace getById(int id) {
    return Arrays.stream(values())
        .filter(race -> race.getId() == id)
        .findFirst()
        .orElse(DEFAULT);
  }
}

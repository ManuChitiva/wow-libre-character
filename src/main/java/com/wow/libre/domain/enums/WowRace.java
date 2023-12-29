package com.wow.libre.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WowRace {
  HUMAN(1, "HUMANO"),
  ORC(2, "ORCO"),
  DWARF(3, "ENANO"),
  NIGHT_ELF(4, "ELFO DE LA NOCHE"),
  UNDEAD(5, "NO MUERTO"),
  TAUREN(6, "TAUREN"),
  GNOME(7, "GNOME"),
  TROLL(8, "TROLL"),
  GOBLIN(9, "GOBLIN"),
  BLOOD_ELF(10, "ELFO DE SANGRE"),
  DRAENEI(11, "DRAENI"),
  WORGEN(22, "WORGEN"),
  PANDAREN_ALLIANCE(24, "PANDARIA"),
  PANDAREN_HORDE(25, "PANDARIA"),
  VOID_ELF(29, "Los Elfos del Vacío"),
  LIGHTFORGED_DRAENEI(30, " Los Draenei Forjados por la Luz"),
  HIGHMOUNTAIN_TAUREN(28, "Tauren Monte Alto"),
  NIGHTBORNE(27, "Nocheterna"),
  DARK_IRON_DWARF(34, "Enano Hierro Negro"),
  VULPERA(35, "Vulpera"),
  MAGHAR_ORC(36, "Orco Mag'har"),
  MECHAGNOME(37, "Mecagnomo"),
  DEFAULT(0, "");

  private final int id;
  private final String raceName;

  WowRace(int id, String raceName) {
    this.id = id;
    this.raceName = raceName;
  }

  public static WowRace getById(int id) {
    return Arrays.stream(values())
            .filter(race -> race.getId() == id)
            .findFirst()
            .orElse(DEFAULT);
  }
}

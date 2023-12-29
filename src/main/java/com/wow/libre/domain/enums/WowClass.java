package com.wow.libre.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WowClass {
  DEATH_KNIGHT(6, "Caballero de la Muerte"),
  HUNTER(3, "Cazador"),
  MAGE(8, "Mago"),
  MONK(10, "Monje"),
  PALADIN(2, "Paladin"),
  PRIEST(5, "Sacerdote"),
  ROGUE(4, "Pícaro"),
  SHAMAN(7, "Chamán"),
  WARRIOR(1, "Guerrero"),
  WARLOCK(9, "Mago"),
  DEFAULT(0, "");

  private final int id;
  private final String raceName;

  WowClass(int id, String raceName) {
    this.id = id;
    this.raceName = raceName;
  }

  // Método para buscar una raza por su ID usando Java Stream
  public static WowClass getById(int id) {
    return Arrays.stream(values())
            .filter(race -> race.getId() == id)
            .findFirst()
            .orElse(DEFAULT);
  }
}

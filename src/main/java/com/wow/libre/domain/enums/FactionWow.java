package com.wow.libre.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FactionWow {
  HORDE(1, "Horde"),
  ALLIANCE(2, "Alliance"),
  DEFAULT(0, "");

  private final int id;
  private final String description;

  FactionWow(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public static FactionWow getById(int id) {
    return Arrays.stream(values())
        .filter(race -> race.getId() == id)
        .findFirst()
        .orElse(DEFAULT);
  }
}

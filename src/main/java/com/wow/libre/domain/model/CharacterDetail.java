package com.wow.libre.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wow.libre.domain.enums.WowClass;
import com.wow.libre.domain.enums.WowRace;
import lombok.Data;

@Data
public class CharacterDetail {
  protected Long id;
  protected String name;
  @JsonProperty("race_id")
  protected Integer raceId;
  protected String race;
  @JsonProperty("class")
  protected String classCharacters;
  @JsonProperty("class_id")
  protected Integer classId;
  protected Integer gender;
  protected Integer level;
  protected Integer xp;
  protected Integer money;

  public CharacterDetail() {
  }

  public CharacterDetail(Character character) {
    this.id = character.guid;
    this.name = character.name;
    this.race = WowRace.getById(character.race).getRaceName();
    this.raceId = character.race;
    this.gender = character.gender;
    this.level = character.level;
    this.xp = character.xp;
    this.money = character.money;
    this.classCharacters = WowClass.getById(character.classCharacters).getRaceName();
    this.classId = character.classCharacters;
  }

}

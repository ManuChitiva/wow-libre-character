package com.wow.libre.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wow.libre.domain.enums.WowClass;
import com.wow.libre.domain.enums.WowRace;
import com.wow.libre.domain.model.Character;
import lombok.Data;

@Data
public class CharacterDetail {
  private Long id;
  private String name;
  @JsonProperty("race_id")
  private Integer raceId;
  private String race;
  @JsonProperty("class")
  private String classCharacters;
  @JsonProperty("class_id")
  private Integer classId;
  private Integer gender;
  private Integer level;
  private Integer xp;
  private Integer money;
  @JsonProperty("number_of_tickets")
  private Long numberOfTickets;

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

  public CharacterDetail(Character character, Long numberOfTickets) {
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
    this.numberOfTickets = numberOfTickets;
  }
}

package com.wow.libre.domain.model;

import com.wow.libre.domain.enums.WowClass;
import com.wow.libre.domain.enums.WowRace;
import lombok.Data;

@Data
public class CharacterSocialDetail extends CharacterDetail {
  protected String flags;
  protected String note;


  public CharacterSocialDetail(Character character, CharacterSocial characterSocial) {
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
    this.note = characterSocial.note;
    this.flags = characterSocial.flags == 1 ? "Friend" : "Ignore";
  }

}

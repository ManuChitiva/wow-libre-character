package com.wow.libre.infrastructure.entities;

import com.wow.libre.infrastructure.entities.dto.CharacterSocialId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@IdClass(CharacterSocialId.class)
@Data
@Entity
@Table(name = "character_social")
public class CharacterSocialEntity implements Serializable {

  @Id
  private Long guid;
  @Id
  private Long friend;
  private Long flags;
  private String note;
}

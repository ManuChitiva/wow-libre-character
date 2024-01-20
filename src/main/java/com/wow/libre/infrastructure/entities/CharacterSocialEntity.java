package com.wow.libre.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@IdClass(CharacterSocialEntity.class)
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

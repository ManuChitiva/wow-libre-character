package com.wow.libre.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CharactersDto {
  private List<CharacterDetail> characters;
  @JsonProperty("total_quantity")
  private Integer totalQuantity;
}

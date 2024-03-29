package com.wow.libre.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wow.libre.domain.model.CharacterDetail;
import lombok.Data;

import java.util.List;

@Data
public class CharactersDto {
  private List<CharacterDetail> characters;
  @JsonProperty("total_quantity")
  private Integer totalQuantity;
}

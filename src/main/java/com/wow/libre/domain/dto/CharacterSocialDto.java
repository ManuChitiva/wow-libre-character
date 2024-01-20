package com.wow.libre.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wow.libre.domain.model.CharacterSocialDetail;
import lombok.Data;

import java.util.List;

@Data
public class CharacterSocialDto {
  private List<CharacterSocialDetail> friends;
  @JsonProperty("total_quantity")
  private Integer totalQuantity;
}

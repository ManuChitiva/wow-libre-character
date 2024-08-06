package com.wow.libre.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfessionServicesDto {
    @NotNull
    private Long characterId;
    @NotNull
    private Long skillId;
    @NotNull
    private Long accountId;
    @NotNull
    @NotEmpty
    private String description;
    boolean isPublic;
}

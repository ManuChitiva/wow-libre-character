package com.wow.libre.domain.dto;

import com.wow.libre.domain.model.GuildModel;
import lombok.Data;

import java.util.List;

@Data
public class GuildsDto {
    private List<GuildModel> guilds;
    private int size;
}

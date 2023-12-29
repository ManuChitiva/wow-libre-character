package com.wow.libre.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public class GmTicket {
  public final Long id;
  public final Long type;

  public final Long guid;
  public final String name;
  public final String description;
  public final LocalDateTime createTime;
  public final Long mapId;
  public final LocalDateTime lastModifiedTime;
  public final Long closedBy;
  public final Long assignedTo;
  public final String comment;
  public final String response;
  public final boolean completed;
  public final boolean escalated;
  public final boolean viewed;
  public final boolean needMoreHelp;
  public final boolean resolvedBy;

}

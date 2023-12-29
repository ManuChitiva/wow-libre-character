package com.wow.libre.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public class GmTicketDto {
  public final Long id;
  @JsonProperty("published_by")
  public final String publishedBy;
  @JsonProperty("create_time")
  public final LocalDateTime createTime;
  @JsonProperty("last_modified_time")
  public final LocalDateTime lastModifiedTime;
  @JsonProperty("closed_by")
  public final String closedBy;
  @JsonProperty("assigned_to")
  public final String assignedTo;
  public final String comment;
  public final String response;
  public final boolean completed;
  public final boolean escalated;
  public final boolean viewed;
  public final boolean resolved;
  public final String description;
}

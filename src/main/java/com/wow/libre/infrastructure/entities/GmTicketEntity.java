package com.wow.libre.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gm_ticket")
public class GmTicketEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long type;
  @Column(name = "playerguid")
  private Long playerGuid;
  private String name;
  private String description;
  @Column(name = "createtime")
  private Long createTime;
  @Column(name = "mapid")
  private Long mapId;
  @Column(name = "posx")
  private Long posX;
  @Column(name = "posy")
  private Long posY;
  @Column(name = "posz")
  private Long posZ;
  @Column(name = "lastmodifiedtime")
  private Long lastModifiedTime;
  @Column(name = "closedby")
  private Long closedBy;
  @Column(name = "assignedto")
  private Long assignedTo;
  private String comment;
  private String response;
  private Long completed;
  private Long escalated;
  private Long viewed;
  @Column(name = "needmorehelp")
  private Long needMoreHelp;
  @Column(name = "resolvedby")
  private Long resolved;

}

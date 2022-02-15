package com.app.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Notice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "notice_id")
  private int noticeId;

  @Column(name = "notice_summary")
  private String noticeSummary;

  @Column(name = "notice_details")
  private String noticeDetails;

  @Column(name = "notic_beg_dt")
  private Date noticBegDt;

  @Column(name = "notic_end_dt")
  private Date noticEndDt;

  @Column(name = "create_dt")
  private Date createDt;

  @Column(name = "update_dt")
  private Date updateDt;
}

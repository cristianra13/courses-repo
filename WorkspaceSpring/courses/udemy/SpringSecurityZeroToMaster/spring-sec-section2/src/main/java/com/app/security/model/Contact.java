package com.app.security.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "contact_messages")
public class Contact {

  @Id
  @Column(name = "contact_id")
  private String contactId;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "contact_email")
  private String contactEmail;

  private String subject;

  private String message;

  @Column(name = "create_dt")
  private Date createDt;
}

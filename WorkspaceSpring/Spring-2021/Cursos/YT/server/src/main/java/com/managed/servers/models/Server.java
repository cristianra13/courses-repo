package com.managed.servers.models;

import com.managed.servers.enumeration.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Server {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  @Column(unique = true)
  @NotEmpty(message = "IP Address cannot be empty or null")
  private String ipAddress;
  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;
}

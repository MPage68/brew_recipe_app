package com.pagecomp.brewapp;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Yeast {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private String name;
  private String style;
  private String attenuation;
  private String floculation;
}
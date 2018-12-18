package com.pagecomp.brewapp;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hop {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private String name;

  @Column(name = "alphaacid")
  private String alphaAcid;
  private String flavor;
}
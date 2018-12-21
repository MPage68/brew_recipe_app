package com.pagecomp.brewapp;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  private String name;
  private String style;
  private double ABV;
  private double IBU;
  private double SRM;

  @ManyToOne
  @JoinColumn(name = "Grain1_ID")
  private Grain grain1;

  @ManyToOne
  @JoinColumn(name = "Grain2_ID")
  private Grain grain2;

  @ManyToOne
  @JoinColumn(name = "Grain3_ID")
  private Grain grain3;

  @ManyToOne
  @JoinColumn(name = "Grain4_ID")
  private Grain grain4;

  @ManyToOne
  @JoinColumn(name = "Grain5_ID")
  private Grain grain5;

  @ManyToOne
  @JoinColumn(name = "Hop1_ID")
  private Hop hop1;

  @ManyToOne
  @JoinColumn(name = "Hop2_ID")
  private Hop hop2;

  @ManyToOne
  @JoinColumn(name = "Hop3_ID")
  private Hop hop3;

  @ManyToOne
  @JoinColumn(name = "Hop4_ID")
  private Hop hop4;

  @ManyToOne
  @JoinColumn(name = "Hop5_ID")
  private Hop hop5;

  @ManyToOne
  @JoinColumn(name = "Yeast_ID")
  private Yeast yeast;

}
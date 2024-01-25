package com.Buska.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Projekt")
@Entity
@Data
public class Projekt {

  @Column(name = "ID")
  @Id
  @GeneratedValue
  private Integer id;
  @Column(name = "Megrendelo")
  private String megrendelo;


  @Column(name = "IndulasiDatum")
  private String indulasiDatum;

  @Column(name = "Leiras")
  private String leiras;
  private boolean deleted;

  public Projekt() {
  }

  public Projekt(Integer id, String megrendelo, String indulasiDatum, String leiras,
      boolean deleted) {
    this.id = id;
    this.megrendelo = megrendelo;
    this.indulasiDatum = indulasiDatum;
    this.leiras = leiras;
    this.deleted = deleted;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMegrendelo() {
    return megrendelo;
  }

  public void setMegrendelo(String megrendelo) {
    this.megrendelo = megrendelo;
  }

  public String getIndulasiDatum() {
    return indulasiDatum;
  }

  public void setIndulasiDatum(String indulasiDatum) {
    this.indulasiDatum = indulasiDatum;
  }

  public String getLeiras() {
    return leiras;
  }

  public void setLeiras(String leiras) {
    this.leiras = leiras;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}


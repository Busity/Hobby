package com.Buska.demo.Entity.EntityDTO;

import com.Buska.demo.Entity.Projekt;
import com.Buska.demo.Entity.ProjektMenedzser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ProjektDTO {

  @GeneratedValue
  @Id
  private Integer id;

  @NotBlank(message = "The Project customer cannot be empty.")
  @Size(min = 2, max = 50, message = "The customer must be between 2 and 50 characters long")
  private String megrendelo;

  @ManyToOne
  @JoinColumn(name = "projekt_menedzser_id")
  private ProjektMenedzser projektMenedzser;

  @NotBlank(message = "The Project start date cannot be empty.")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @NotNull(message = "The departure time field cannot be empty")
  @Future(message = "The departure time must be in the future")
  private String indulasiDatum;

  @NotBlank(message = "The description field cannot be empty")
  @Size(max = 500, min = 50, message = "The maximum length of the description field can be 500 characters")
  private String leiras;

  private boolean deleted;

  public ProjektDTO() {
  }

  public ProjektDTO(String megrendelo, ProjektMenedzser projektMenedzser, String indulasiDatum, String leiras, boolean deleted) {
    this.megrendelo = megrendelo;
    this.projektMenedzser = projektMenedzser;
    this.indulasiDatum = indulasiDatum;
    this.leiras = leiras;
    this.deleted = deleted;
  }

  public ProjektDTO(Projekt projekt) {
  }

  public ProjektDTO(ProjektDTO projektDTO) {
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

  public ProjektMenedzser getProjektMenedzser() {
    return projektMenedzser;
  }

  public void setProjektMenedzser(ProjektMenedzser projektMenedzser) {
    this.projektMenedzser = projektMenedzser;
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
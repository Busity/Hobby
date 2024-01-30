package com.Buska.demo.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.Data;


@Data
@Entity
public class Alkalmazott {

  @GeneratedValue
  @Id
  @Column(name = "ID")
  private Integer id;

  @Column(name = "Name")
  private String nev;

  @Column(name = "Adress")
  private String lakcim;

  @Column(name = "Bday", columnDefinition = "date")
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy/MM/dd")
  private LocalDate szuletesi_Datum;

  @Column(name = "PhoneNumber")
  private String telefonszam;

  @Column(name = "email", unique = true)
  private String email;

  private boolean deleted;

  public Alkalmazott() {
  }

  @SuppressWarnings("unused")
  public Alkalmazott(Integer id, String nev, String lakcim, LocalDate szuletesi_Datum,
      String telefonszam, String email, boolean deleted) {
    this.id = id;
    this.nev = nev;
    this.lakcim = lakcim;
    this.szuletesi_Datum = szuletesi_Datum;
    this.telefonszam = telefonszam;
    this.email = email;
    this.deleted = deleted;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNev() {
    return nev;
  }

  public void setNev(String nev) {
    this.nev = nev;
  }

  public String getLakcim() {
    return lakcim;
  }

  public void setLakcim(String lakcim) {
    this.lakcim = lakcim;
  }

  public LocalDate getSzuletesi_Datum() {
    return szuletesi_Datum;
  }

  public void setSzuletesi_Datum(LocalDate szuletesiDatum) {
    this.szuletesi_Datum = szuletesiDatum;
  }

  public String getTelefonszam() {
    return telefonszam;
  }

  public void setTelefonszam(String telefonszam) {
    this.telefonszam = telefonszam;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}

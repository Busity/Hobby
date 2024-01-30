package com.Buska.demo.Entity;


import com.Buska.demo.Entity.EntityDTO.AlkalmazottDTO;
import com.Buska.demo.Entity.EntityDTO.ProjektDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjektMenedzser extends Alkalmazott {

  @OneToMany(fetch = FetchType.EAGER)
  private List<ProjektDTO> projektek;


  @OneToMany(fetch = FetchType.EAGER)
  private List<AlkalmazottDTO> beosztottak;


  public ProjektMenedzser() {
  }

  public ProjektMenedzser(Integer id, String nev, String lakcim, LocalDate szuletesiDatum,
      String telefonszam, String email, boolean deleted) {
    super(id, nev, lakcim, szuletesiDatum, telefonszam, email, deleted);
  }

  public ProjektMenedzser(Integer id, String nev, String lakcim, LocalDate szuletesi_Datum,
      String telefonszam, String email, boolean deleted, List<ProjektDTO> projektek,
      List<AlkalmazottDTO> beosztottak) {
    super(id, nev, lakcim, szuletesi_Datum, telefonszam, email, deleted);
    this.projektek = projektek;
    this.beosztottak = beosztottak;
  }

  public List<ProjektDTO> getProjektek() {
    return projektek != null ? projektek : Collections.emptyList();
  }

  public void setProjektek(List<ProjektDTO> projektek) {
    this.projektek = projektek;
  }

  public List<AlkalmazottDTO> getBeosztottak() {
    return beosztottak != null ? beosztottak : Collections.emptyList();
  }

  public void setBeosztottak(List<AlkalmazottDTO> beosztottak) {
    this.beosztottak = beosztottak;
  }

}
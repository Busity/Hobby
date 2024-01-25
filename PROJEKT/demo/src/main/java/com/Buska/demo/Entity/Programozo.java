package com.Buska.demo.Entity;


import com.Buska.demo.Enum.Feladatkor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
public class Programozo extends Alkalmazott {


  public Programozo(Integer id, String nev, String lakcim, Date szuletesi_Datum, String telefonszam,
      String email, boolean deleted) {
    super(id, nev, lakcim, szuletesi_Datum, telefonszam, email, deleted);
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "Duties")
  private Feladatkor feladatkor;

  private boolean gyakornok;
  @JoinColumn(name = "Projects")
  @OneToMany(fetch = FetchType.EAGER)
  private List<Projekt> projektek;
  @JoinColumn(name = "ProjectManager")
  @ManyToOne(fetch = FetchType.EAGER)
  private ProjektMenedzser projektmenedzser;
  @JoinColumn(name = "Subordinates")
  @OneToMany(fetch = FetchType.EAGER)
  private List<Alkalmazott> beosztottak;


  public Programozo(Integer id, String nev, String lakcim, Date szuletesi_Datum,
      String telefonszam, String email, boolean deleted, Feladatkor feladatkor, boolean gyakornok,
      List<Projekt> projektek, ProjektMenedzser projektmenedzser, List<Alkalmazott> beosztottak) {
    super(id, nev, lakcim, szuletesi_Datum, telefonszam, email, deleted);
    this.feladatkor = feladatkor;
    this.gyakornok = gyakornok;
    this.projektek = projektek;
    this.projektmenedzser = projektmenedzser;
    this.beosztottak = beosztottak;
  }

  public Programozo() {
    this.gyakornok = isGyakornok();
    this.projektek = new ArrayList<>();
    this.beosztottak = new ArrayList<>();
  }

  public Programozo(Integer id, String nev, String lakcim, Date szuletesi_Datum,
      String telefonszam, String email, boolean deleted, boolean gyakornok, List<Projekt> projektek,
      ProjektMenedzser projektmenedzser, List<Alkalmazott> beosztottak) {
    super(id, nev, lakcim, szuletesi_Datum, telefonszam, email, deleted);
    this.gyakornok = gyakornok;
    this.projektek = projektek;
    this.projektmenedzser = projektmenedzser;
    this.beosztottak = beosztottak;

  }


  public Feladatkor getFeladatkor() {
    return feladatkor;
  }

  public void setFeladatkor(Feladatkor feladatkor) {
    this.feladatkor = feladatkor;
  }

  public boolean isGyakornok() {
    return gyakornok;
  }

  public void setGyakornok(boolean gyakornok) {
    this.gyakornok = gyakornok;
  }

  public List<Projekt> getProjektek() {
    return projektek;
  }

  public void setProjektek(List<Projekt> projektek) {
    this.projektek = projektek;
  }

  public ProjektMenedzser getProjektmenedzser() {
    return projektmenedzser;
  }

  public void setProjektmenedzser(ProjektMenedzser projektmenedzser) {
    this.projektmenedzser = projektmenedzser;
  }

  public List<Alkalmazott> getBeosztottak() {
    return beosztottak;
  }

  public void setBeosztottak(List<Alkalmazott> beosztottak) {
    this.beosztottak = beosztottak;
  }
}

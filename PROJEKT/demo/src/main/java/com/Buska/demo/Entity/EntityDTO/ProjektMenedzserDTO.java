package com.Buska.demo.Entity.EntityDTO;


import com.Buska.demo.Entity.ProjektMenedzser;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ProjektMenedzserDTO extends AlkalmazottDTO {

  @NotNull
  private List<ProjektDTO> projektek;
  @NotNull
  private List<AlkalmazottDTO> beosztottak;


  public ProjektMenedzserDTO() {
    super();
  }


  public ProjektMenedzserDTO(ProjektMenedzser projektMenedzser) {
    super(projektMenedzser);

    if (projektMenedzser != null) {
      this.projektek = projektMenedzser.getProjektek().stream()
          .map(ProjektDTO::new)
          .collect(Collectors.toList());
      this.beosztottak = projektMenedzser.getBeosztottak().stream()
          .map(AlkalmazottDTO::new)
          .collect(Collectors.toList());
    } else {
      this.projektek = Collections.emptyList();
      this.beosztottak = Collections.emptyList();
    }
  }


  public List<ProjektDTO> getProjektek() {
    return projektek;
  }

  public void setProjektek(List<ProjektDTO> projektek) {
    this.projektek = projektek;
  }

  public List<AlkalmazottDTO> getBeosztottak() {
    return beosztottak;
  }

  public void setBeosztottak(List<AlkalmazottDTO> beosztottak) {
    this.beosztottak = beosztottak;
  }
}
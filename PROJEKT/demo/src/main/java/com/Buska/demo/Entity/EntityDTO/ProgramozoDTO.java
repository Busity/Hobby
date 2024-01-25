package com.Buska.demo.Entity.EntityDTO;


import com.Buska.demo.Entity.Programozo;
import com.Buska.demo.Enum.Feladatkor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import jdk.jfr.BooleanFlag;


public class ProgramozoDTO extends AlkalmazottDTO {


  @Enumerated(EnumType.STRING)
  private Feladatkor feladatkor;
  @BooleanFlag
  private boolean gyakornok;
  @NotNull
  private List<ProjektDTO> projektek;
  @NotBlank
  private ProjektMenedzserDTO projektmenedzser;
  @NotNull
  private List<AlkalmazottDTO> beosztottak;


  public ProgramozoDTO() {
  }

  public ProgramozoDTO(Programozo programozo) {
    super(programozo);
    this.feladatkor = programozo.getFeladatkor();
    this.gyakornok = programozo.isGyakornok();
    this.projektek = programozo.getProjektek().stream()
        .map(ProjektDTO::new)
        .collect(Collectors.toList());
    this.projektmenedzser = new ProjektMenedzserDTO(programozo.getProjektmenedzser());
    this.beosztottak = programozo.getBeosztottak().stream()
        .map(AlkalmazottDTO::new)
        .collect(Collectors.toList());
  }

  public ProgramozoDTO(ProjektMenedzserDTO projektMenedzserDTO) {
  }
}
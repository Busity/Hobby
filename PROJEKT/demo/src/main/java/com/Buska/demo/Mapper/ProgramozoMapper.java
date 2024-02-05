package com.Buska.demo.Mapper;


import com.Buska.demo.Entity.EntityDTO.ProgramozoDTO;
import com.Buska.demo.Entity.Programozo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramozoMapper {

  ProgramozoMapper INSTANCE = Mappers.getMapper(ProgramozoMapper.class);


  @Mapping(source = "id", target = "id")
  @Mapping(source = "nev", target = "nev")
  @Mapping(source = "lakcim", target = "lakcim")
  @Mapping(source = "szuletesi_Datum", target = "szuletesi_Datum")
  @Mapping(source = "telefonszam", target = "telefonszam")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "deleted", target = "deleted")
  @Mapping(source = "feladatkor", target = "feladatkor")
  @Mapping(source = "gyakornok", target = "gyakornok")
  @Mapping(source = "projektek", target = "projektek")
  @Mapping(source = "projektmenedzser", target = "projektmenedzser")
  @Mapping(source = "beosztottak", target = "beosztottak")
  ProgramozoDTO programozoToProgramozoDTO(Programozo programozo);

  Programozo programozoDTOToProgramozo(ProgramozoDTO programozoDTO);
}
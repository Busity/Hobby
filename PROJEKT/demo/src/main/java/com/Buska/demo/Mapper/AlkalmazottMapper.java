package com.Buska.demo.Mapper;


import com.Buska.demo.Entity.Alkalmazott;
import com.Buska.demo.Entity.EntityDTO.AlkalmazottDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlkalmazottMapper {

  AlkalmazottMapper INSTANCE = Mappers.getMapper(AlkalmazottMapper.class);

  @Mapping(target = "id", source = "alkalmazott.id")
  @Mapping(target = "nev", source = "alkalmazott.nev")
  @Mapping(target = "lakcim", source = "alkalmazott.lakcim")
  @Mapping(target = "szuletesi_Datum", source = "alkalmazott.szuletesi_Datum")
  @Mapping(target = "telefonszam", source = "alkalmazott.telefonszam")
  @Mapping(target = "email", source = "alkalmazott.email")
  @Mapping(target = "deleted", source = "alkalmazott.deleted")
  AlkalmazottDTO alkalmazottToAlkalmazottDTO(Alkalmazott alkalmazott);
}

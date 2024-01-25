package com.Buska.demo.Mapper;


import com.Buska.demo.Entity.EntityDTO.ProjektMenedzserDTO;
import com.Buska.demo.Entity.ProjektMenedzser;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjektMenedzserMapper {

  ProjektMenedzserMapper INSTANCE = Mappers.getMapper(ProjektMenedzserMapper.class);

  @Mapping(source = "projektek", target = "projektek")
  @Mapping(source = "beosztottak", target = "beosztottak")
  ProjektMenedzserDTO toDTO(ProjektMenedzser projektMenedzser);

  List<ProjektMenedzserDTO> toDTOList(List<ProjektMenedzser> projektMenedzserList);
}

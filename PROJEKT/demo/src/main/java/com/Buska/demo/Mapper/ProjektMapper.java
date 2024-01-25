package com.Buska.demo.Mapper;


import com.Buska.demo.Entity.EntityDTO.ProjektDTO;
import com.Buska.demo.Entity.Projekt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjektMapper {

  ProjektMapper INSTANCE = Mappers.getMapper(ProjektMapper.class);

  @Mapping(source = "megrendelo", target = "megrendelo")
  @Mapping(source = "indulasiDatum", target = "indulasiDatum")
  @Mapping(source = "leiras", target = "leiras")
  @Mapping(source = "deleted", target = "deleted")
  ProjektDTO toDTO(Projekt projekt);

}
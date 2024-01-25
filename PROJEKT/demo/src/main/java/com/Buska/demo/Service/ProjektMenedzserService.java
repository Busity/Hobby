package com.Buska.demo.Service;


import com.Buska.demo.Entity.EntityDTO.ProjektMenedzserDTO;
import com.Buska.demo.Entity.ProjektMenedzser;
import com.Buska.demo.Mapper.ProjektMenedzserMapper;
import com.Buska.demo.Repository.ProjektMenedzserRepository;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjektMenedzserService implements ProjektMenedzserMapper {


  @Autowired
  private ProjektMenedzserRepository projektMenedzserRepository;


  public List<ProjektMenedzserDTO> getAllProjektMenedzser() {
    List<ProjektMenedzser> projektmenedzserlist = projektMenedzserRepository.findAll();
    if (projektmenedzserlist.isEmpty()) {
      throw new IllegalStateException("There are no Project Managers in the database.");
    }

    return projektmenedzserlist.stream()
        .map(ProjektMenedzserDTO::new)
        .sorted(Comparator.comparing(ProjektMenedzserDTO::getId))
        .collect(Collectors.toList());
  }


  public ProjektMenedzserDTO getProjektMenedzserById(Integer id) {
    if (id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    ProjektMenedzser projektMenedzser = projektMenedzserRepository.findById(id).orElse(null);
    if (projektMenedzser == null) {
      return null;
    }

    return new ProjektMenedzserDTO(projektMenedzser);
  }

  @Transactional
  public Integer createProjektMenedzser(ProjektMenedzserDTO projektMenedzserDTO) {
    validateProjektMenedzserCreateDTO(projektMenedzserDTO);

    ProjektMenedzser projektMenedzser = new ProjektMenedzser();
    projektMenedzser.setNev(projektMenedzserDTO.getNev());
    projektMenedzser.setLakcim(projektMenedzserDTO.getLakcim());
    projektMenedzser.setSzuletesi_Datum(projektMenedzserDTO.getSzuletesi_Datum());
    projektMenedzser.setEmail(projektMenedzserDTO.getEmail());
    projektMenedzser.setTelefonszam(projektMenedzserDTO.getTelefonszam());
    projektMenedzser.setDeleted(false);

    if (projektMenedzserRepository.existsByEmail(projektMenedzser.getEmail())) {
      throw new IllegalArgumentException("This email address already exists.");
    }

    if (projektMenedzserRepository.existsByTelefonszam(projektMenedzser.getTelefonszam())) {
      throw new IllegalArgumentException("This phone number already exists.");
    }

    ProjektMenedzser savedProjektMenedzser = projektMenedzserRepository.save(projektMenedzser);
    return savedProjektMenedzser.getId();
  }

  private void validateProjektMenedzserCreateDTO(ProjektMenedzserDTO projektMenedzserDTO) {
    if (projektMenedzserDTO == null) {
      throw new IllegalArgumentException("The ProjectManagerDTO object cannot be null.");
    }
    if (!projektMenedzserDTO.getTelefonszam().matches("\\d{8,20}")) {
      throw new IllegalArgumentException(
          "The phone number must be between 8 and 20 characters long and can only contain numerical digits.");
    }
    if (projektMenedzserDTO.getNev() == null || projektMenedzserDTO.getNev().isBlank()) {
      throw new IllegalArgumentException("The name of the ProjectManagerDTO cannot be empty.");
    }
  }

  @Transactional
  public Integer updateProjektMenedzser(Integer id, ProjektMenedzserDTO projektMenedzserDTO) {
    if (id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    ProjektMenedzser PMEntity = projektMenedzserRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(

            "Not found ProjectManager with identifier: " + id));

    try {
      validateProjektMenedzserUpdateDTO(projektMenedzserDTO);

      PMEntity.setBeosztottak(projektMenedzserDTO.getBeosztottak());
      PMEntity.setNev(projektMenedzserDTO.getNev());
      PMEntity.setLakcim(projektMenedzserDTO.getLakcim());
      PMEntity.setSzuletesi_Datum(projektMenedzserDTO.getSzuletesi_Datum());
      PMEntity.setTelefonszam(projektMenedzserDTO.getTelefonszam());
      PMEntity.setEmail(projektMenedzserDTO.getEmail());
      PMEntity.setDeleted(projektMenedzserDTO.isDeleted());
      PMEntity.setProjektek(projektMenedzserDTO.getProjektek());

      ProjektMenedzser updatedProjektMenedzserEntity = projektMenedzserRepository.save(
          PMEntity);

      return updatedProjektMenedzserEntity.getId();
    } catch (IllegalArgumentException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException("An error occurred while saving the modification.", ex);
    }
  }

  private void validateProjektMenedzserUpdateDTO(ProjektMenedzserDTO projektMenedzserDTO) {
    if (projektMenedzserDTO == null) {
      throw new IllegalArgumentException("The ProjectManagerUpdateDTO object cannot be null.");
    }
    if (!projektMenedzserDTO.getTelefonszam().matches("\\d{8,20}")) {
      throw new IllegalArgumentException(
          "The phone number must be between 8 and 20 characters long and can only contain numerical digits.");
    }
    if (projektMenedzserDTO.getNev() == null || projektMenedzserDTO.getNev().isEmpty()) {
      throw new IllegalArgumentException("The name of the ProjectManagerDTO cannot be empty.");
    }

  }


  @Transactional
  public void deleteProjektMenedzser(Integer id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    ProjektMenedzser existingEmployee = projektMenedzserRepository.findById(id).orElse(null);
    if (existingEmployee == null) {
      throw new IllegalArgumentException("Not found ProjectManager with identifier: " + id);
    }

    try {
      existingEmployee.setDeleted(true);
      projektMenedzserRepository.save(existingEmployee);
    } catch (Exception e) {
      throw new RuntimeException("Deletion of the Project Manager failed.", e);
    }
  }


  @Override
  public ProjektMenedzserDTO toDTO(ProjektMenedzser projektMenedzser) {
    return null;
  }

  @Override
  public List<ProjektMenedzserDTO> toDTOList(List<ProjektMenedzser> projektMenedzserList) {
    return null;
  }
}
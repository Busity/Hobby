package com.Buska.demo.Service;


import com.Buska.demo.Entity.EntityDTO.ProjektDTO;
import com.Buska.demo.Entity.Projekt;
import com.Buska.demo.Repository.ProjektRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjektService {

  private final ProjektRepo projektRepo;

  public ProjektService(ProjektRepo projektRepo) {
    this.projektRepo = projektRepo;
  }


  @Transactional
  public List<ProjektDTO> getAllProjekts() {
    List<Projekt> projekts = projektRepo.findAll();
    return projekts.stream().map(ProjektDTO::new)
        .collect(Collectors.toList());
  }


  @Transactional
  public ProjektDTO getProjektById(Integer id) {
    Projekt projekt = projektRepo.findById(id).orElse(null);
    if (projekt != null) {
      return new ProjektDTO(projekt);
    } else {
      return new ProjektDTO();
    }
  }


  @Transactional
  public Integer createProjekt(ProjektDTO projektDTO) {
    if (projektDTO == null) {
      throw new IllegalArgumentException("The ProjectDTO object cannot be null.");
    }

    if (projektDTO.getLeiras() == null || projektDTO.getLeiras().isBlank() ||
        projektDTO.getIndulasiDatum() == null || projektDTO.getIndulasiDatum().isBlank() ||
        projektDTO.getMegrendelo() == null || projektDTO.getMegrendelo().isBlank()) {
      throw new IllegalArgumentException("The fields of the ProjectDTO cannot be empty.");
    }

    Projekt projekt = new Projekt();
    projekt.setLeiras(projektDTO.getLeiras());
    projekt.setIndulasiDatum(projektDTO.getIndulasiDatum());
    projekt.setMegrendelo(projektDTO.getMegrendelo());

    Projekt savedProjekt = projektRepo.save(projekt);

    ProjektDTO savedProjektDTO = new ProjektDTO();
    savedProjektDTO.setId(savedProjekt.getId());
    savedProjektDTO.setLeiras(savedProjekt.getLeiras());
    savedProjektDTO.setIndulasiDatum(savedProjekt.getIndulasiDatum());
    savedProjektDTO.setMegrendelo(savedProjekt.getMegrendelo());

    return savedProjektDTO.getId();
  }


  @Transactional
  public Integer updateProjekt(Integer id, @Valid @RequestBody ProjektDTO projektDTO) {
    Projekt existingProjekt = projektRepo.findById(id).orElse(null);

    if (existingProjekt != null) {

      if (projektDTO.getLeiras() == null || projektDTO.getLeiras().isBlank() ||
          projektDTO.getIndulasiDatum() == null || projektDTO.getIndulasiDatum().isBlank() ||
          projektDTO.getMegrendelo() == null || projektDTO.getMegrendelo().isBlank()) {
        throw new IllegalArgumentException("The fields of the ProjectDTO cannot be empty.");
      }

      existingProjekt.setLeiras(projektDTO.getLeiras());
      existingProjekt.setIndulasiDatum(projektDTO.getIndulasiDatum());
      existingProjekt.setMegrendelo(projektDTO.getMegrendelo());

      Projekt savedProjekt = projektRepo.save(existingProjekt);

      ProjektDTO savedProjektDTO = new ProjektDTO();
      savedProjektDTO.setId(savedProjekt.getId());
      savedProjektDTO.setLeiras(savedProjekt.getLeiras());
      savedProjektDTO.setIndulasiDatum(savedProjekt.getIndulasiDatum());
      savedProjektDTO.setMegrendelo(savedProjekt.getMegrendelo());

      return savedProjektDTO.getId();
    }
    return null;
  }


  public void deleteProjekt(Integer id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    Projekt projekt = projektRepo.findById(id).orElse(null);
    if (projekt == null) {
      throw new IllegalArgumentException("Not found Project with identifier: " + id);
    }

    try {
      projekt.setDeleted(true);
      projektRepo.save(projekt);
    } catch (Exception e) {
      throw new RuntimeException("Deletion of the project failed.", e);
    }
  }
}
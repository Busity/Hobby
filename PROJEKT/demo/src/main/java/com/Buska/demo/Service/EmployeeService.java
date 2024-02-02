package com.Buska.demo.Service;


import com.Buska.demo.Entity.EntityDTO.ProgramozoDTO;
import com.Buska.demo.Entity.Programozo;
import com.Buska.demo.Repository.ProgramozoRepo;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

  @Autowired
  private final ProgramozoRepo programozoRepo;

  public EmployeeService(ProgramozoRepo programozoRepo) {
    this.programozoRepo = programozoRepo;
  }

  public List<ProgramozoDTO> getAllProgramozo() {
    List<Programozo> programozoList;
    programozoList = programozoRepo.findAll();
    if (programozoList.isEmpty()) {
      throw new IllegalStateException("There are no Programmers in the database.");
    }

    return programozoList.stream()
        .map(ProgramozoDTO::new)
        .sorted(Comparator.comparing(ProgramozoDTO::getId))
        .collect(Collectors.toList());
  }


  public ProgramozoDTO getProgramozoById(Integer id) {
    if (id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    Programozo programozo = programozoRepo.findById(id).orElse(null);
    if (programozo == null) {
      return null;
    }

    return new ProgramozoDTO(programozo);
  }

  public ProgramozoDTO createProgramozo(ProgramozoDTO programozoDTO) {
    validateProgramozoCreateDTO(programozoDTO);
    Programozo programmer = new Programozo();
    programmer.setNev(programozoDTO.getNev());
    programmer.setLakcim(programozoDTO.getLakcim());
    programmer.setSzuletesi_Datum(programozoDTO.getSzuletesi_Datum());
    programmer.setTelefonszam(programozoDTO.getTelefonszam());
    programmer.setEmail(programozoDTO.getEmail());
    programmer.setDeleted(false);
    if (programozoRepo.existsByEmail(programmer.getEmail())) {
      throw new IllegalArgumentException("This email address already exists.");
    }
    if (programozoRepo.existsByTelefonszam(programmer.getTelefonszam())) {
      throw new IllegalArgumentException("This phone number already exists.");
    }
    programozoRepo.save(programmer);
    return new ProgramozoDTO(programmer);
  }

  private void validateProgramozoCreateDTO(ProgramozoDTO programozoCreateDTO) {
    if (programozoCreateDTO == null) {
      throw new IllegalArgumentException("The ProgrammerCreateDTO object cannot be null.");
    }
    if (!programozoCreateDTO.getTelefonszam().matches("\\d{8,20}")) {
      throw new IllegalArgumentException(
          "The phone number must be between 8 and 20 characters long and can only contain numerical digits.");
    }
    if (programozoCreateDTO.getNev() == null || programozoCreateDTO.getNev().isBlank()) {
      throw new IllegalArgumentException("The name of the ProgrammerCreateDTO cannot be empty.");
    }
  }


  public Integer updateProgramozo(Integer id, ProgramozoDTO programozoDTO) {
    validateProgramozoUpdateDTO(programozoDTO);

    if (id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    Programozo existingEmployee = programozoRepo.findById(id).orElse(null);

    if (existingEmployee != null) {
      existingEmployee.setNev(programozoDTO.getNev());
      existingEmployee.setLakcim(programozoDTO.getLakcim());
      existingEmployee.setSzuletesi_Datum(programozoDTO.getSzuletesi_Datum());
      existingEmployee.setTelefonszam(programozoDTO.getTelefonszam());
      existingEmployee.setEmail(programozoDTO.getEmail());
      existingEmployee.setDeleted(programozoDTO.isDeleted());

      if (!existingEmployee.getEmail().equals(programozoDTO.getEmail())
          && programozoRepo.existsByEmail(programozoDTO.getEmail())) {
        throw new IllegalArgumentException("This email address already exists.");
      }

      if (!existingEmployee.getTelefonszam().equals(programozoDTO.getTelefonszam())
          && programozoRepo.existsByTelefonszam(programozoDTO.getTelefonszam())) {
        throw new IllegalArgumentException("This phone number already exists.");
      }
      programozoRepo.save(existingEmployee);
      return existingEmployee.getId();
    } else {
      return null;
    }
  }


  private void validateProgramozoUpdateDTO(ProgramozoDTO programozoDTO) {
    if (programozoDTO == null) {
      throw new IllegalArgumentException("The ProgramozoUpdateDTO object cannot be null.");
    }
    if (!programozoDTO.getTelefonszam().matches("\\d{8,20}")) {
      throw new IllegalArgumentException(
          "The phone number must be between 8 and 20 characters long and can only contain numbers.");
    }
  }

  public void deleteProgramozo(Integer id) {
    if (id <= 0) {
      throw new IllegalArgumentException("The identifier is invalid.");
    }

    Programozo existingEmployee = programozoRepo.findById(id).orElse(null);
    if (existingEmployee != null)
      existingEmployee.setDeleted(true);
    else {
      throw new IllegalArgumentException("Not found with Programmer ID:" + id);
    }
  }


}


package com.Buska.demo.Controller;


import com.Buska.demo.Entity.EntityDTO.ProjektMenedzserDTO;
import com.Buska.demo.Service.ProjektMenedzserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProjMan")
public class ProjektMenedzserController {

  @Autowired
  private final ProjektMenedzserService projektMenedzserService;

  @Autowired
  public ProjektMenedzserController(ProjektMenedzserService projektMenedzserService) {
    this.projektMenedzserService = projektMenedzserService;
  }

  @GetMapping("/mind1")
  public ResponseEntity<?> getAllEmployees1() {
    try {
      List<ProjektMenedzserDTO> employees = projektMenedzserService.getAllProjektMenedzser();
      return new ResponseEntity<>(employees, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @GetMapping("/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
    try {
      ProjektMenedzserDTO projektMenedzserDTO = projektMenedzserService.getProjektMenedzserById(id);
      return new ResponseEntity<>(projektMenedzserDTO, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> createProjektMenedzser(
      @RequestBody @Valid ProjektMenedzserDTO projektMenedzserDTO) {
    try {
      Integer createdProjektMenedzser = projektMenedzserService.createProjektMenedzser(
          projektMenedzserDTO);
      return new ResponseEntity<>(createdProjektMenedzser, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateEmployee(@PathVariable Integer id,
      @RequestBody @Valid ProjektMenedzserDTO projektMenedzserDTO) {
    try {
      Integer updatedProjektMenedzser = projektMenedzserService.updateProjektMenedzser(
          id, projektMenedzserDTO);
      return new ResponseEntity<>(updatedProjektMenedzser, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
    try {
      projektMenedzserService.deleteProjektMenedzser(id);
      return new ResponseEntity<>("The project has been successfully deleted!",
          HttpStatus.NO_CONTENT);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
package com.Buska.demo.Controller;

import com.Buska.demo.Entity.EntityDTO.ProjektDTO;
import com.Buska.demo.Service.ProjektService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/projektek")
public class ProjektController {

  private final ProjektService projektService;


  public ProjektController(ProjektService projektService) {
    this.projektService = projektService;
  }

  @GetMapping
  public ResponseEntity<?> getAllProjekts() {
    ResponseEntity<?> result;
    try {
      List<ProjektDTO> projektDTOs = projektService.getAllProjekts();
      result = new ResponseEntity<>(projektDTOs, HttpStatus.OK);
    } catch (Exception e) {
      result = new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return result;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProjektById(@PathVariable Integer id) {
    try {
      ProjektDTO projektDTO = projektService.getProjektById(id);
      return new ResponseEntity<>(projektDTO, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping
  public ResponseEntity<?> createProjekt(@RequestBody @Valid ProjektDTO projektDTO) {
    try {
      Integer createdProjektDTO = projektService.createProjekt(projektDTO);
      return new ResponseEntity<>(createdProjektDTO, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProjekt(@PathVariable @Valid Integer id,
      @RequestBody @Valid ProjektDTO projektDTO) {
    try {
      Integer updatedProjektDTO = projektService.updateProjekt(id, projektDTO);
      return new ResponseEntity<>(updatedProjektDTO, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProjekt(@PathVariable Integer id) {
    ResponseEntity<?> result;
    try {
      projektService.deleteProjekt(id);
      result = new ResponseEntity<>("The projectmanager has been successfully deleted!",
          HttpStatus.NO_CONTENT);

    } catch (IllegalArgumentException e) {
      result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (EntityNotFoundException e) {
      result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      result = new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return result;
  }
}
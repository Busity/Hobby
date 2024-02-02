package com.Buska.demo.Controller;


import static com.Buska.demo.Enum.Exception.ENTITY_NOT_FOUND_MESSAGE;

import com.Buska.demo.Entity.EntityDTO.ProgramozoDTO;
import com.Buska.demo.Repository.ProgramozoRepo;
import com.Buska.demo.Service.EmployeeService;
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
@RequestMapping("/proba")
public class EmployeeController {


  @Autowired
  ProgramozoRepo programozoRepo;
  @Autowired
  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(ProgramozoRepo programozoRepo) {
    this.programozoRepo = programozoRepo;
  }


  @GetMapping("/programozok")
  public ResponseEntity<List<ProgramozoDTO>> getAllEmployees() {
    List<ProgramozoDTO> programozok = employeeService.getAllProgramozo();
    if (programozok.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(programozok, HttpStatus.OK);
  }


  @GetMapping("/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
    try {
      ProgramozoDTO programozoDTO = employeeService.getProgramozoById(id);
      return new ResponseEntity<>(programozoDTO, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/createProgramozo")
  public ResponseEntity<?> createProgramozo(@RequestBody @Valid ProgramozoDTO programozoDTO) {
    try {
      ProgramozoDTO savedProgramozo = employeeService.createProgramozo(programozoDTO);
      return new ResponseEntity<>(savedProgramozo, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred: " + e.getMessage() + e.initCause(new Throwable("Try Again")),
          HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProgramozo(@PathVariable @Valid Integer id,
      @RequestBody @Valid ProgramozoDTO programozoDTO) {
    Integer updatedId = employeeService.updateProgramozo(id, programozoDTO);

    if (updatedId != null) {
      return ResponseEntity.ok(updatedId);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
    try {
      employeeService.deleteProgramozo(id);


      return ResponseEntity.ok("The programmer has been successfully deleted!");
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(ENTITY_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("An unknown error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

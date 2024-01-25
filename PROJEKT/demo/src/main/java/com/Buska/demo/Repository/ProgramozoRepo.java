package com.Buska.demo.Repository;

import com.Buska.demo.Entity.Programozo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramozoRepo extends JpaRepository<Programozo, Integer> {

  boolean existsByEmail(String email);


  boolean existsByTelefonszam(String telefonszam);
}
package com.Buska.demo.Repository;


import com.Buska.demo.Entity.ProjektMenedzser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjektMenedzserRepository extends JpaRepository<ProjektMenedzser, Integer> {

  boolean existsByEmail(String email);


  boolean existsByTelefonszam(String telefonszam);
}
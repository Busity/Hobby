package com.Buska.demo.Repository;

import com.Buska.demo.Entity.Projekt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjektRepo extends JpaRepository<Projekt, Integer> {

  <S extends Projekt> List<S> saveAllAndFlush(Iterable<S> entities);

}


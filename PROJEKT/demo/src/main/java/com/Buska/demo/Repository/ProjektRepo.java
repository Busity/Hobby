package com.Buska.demo.Repository;

import com.Buska.demo.Entity.Projekt;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjektRepo extends JpaRepository<Projekt, Integer> {

  @Override
  <S extends Projekt> Page<S> findAll(Example<S> example, Pageable pageable);

  <S extends Projekt> List<S> saveAllAndFlush(Iterable<S> entities);

}


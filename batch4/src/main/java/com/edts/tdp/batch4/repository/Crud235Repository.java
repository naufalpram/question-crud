package com.edts.tdp.batch4.repository;

import com.edts.tdp.batch4.model.Crud235;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Crud235Repository extends JpaRepository<Crud235, Integer> {

    Optional<Crud235> findCrud235ById(int id);

}

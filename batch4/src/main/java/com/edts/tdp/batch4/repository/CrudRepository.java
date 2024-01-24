package com.edts.tdp.batch4.repository;

import com.edts.tdp.batch4.model.Crud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepository extends JpaRepository<Crud, Integer> {
    Optional<Crud> findByIdAndQuestionNumber(int id, int questionNumber);
    Page<Crud> findAll(Pageable pageable);
    Page<Crud> findAllByQuestionNumber(int questionNumber, Pageable pageable);
}

package com.edts.tdp.batch4.repository;

import com.edts.tdp.batch4.model.Crud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<Crud, Integer> {
}

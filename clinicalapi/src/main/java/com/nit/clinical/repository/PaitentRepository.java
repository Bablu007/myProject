package com.nit.clinical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nit.clinical.entity.Patient;
@Repository
public interface PaitentRepository extends JpaRepository<Patient, Integer> {

}

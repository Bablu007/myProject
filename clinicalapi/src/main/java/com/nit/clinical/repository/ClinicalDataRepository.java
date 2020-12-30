package com.nit.clinical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nit.clinical.entity.ClinicalData;
@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

 public	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId,String componentName);

}

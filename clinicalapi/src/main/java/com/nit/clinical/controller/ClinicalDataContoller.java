package com.nit.clinical.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.clinical.dto.ClinicalDataRequest;
import com.nit.clinical.entity.ClinicalData;
import com.nit.clinical.entity.Patient;
import com.nit.clinical.repository.ClinicalDataRepository;
import com.nit.clinical.repository.PaitentRepository;
import com.nit.clinical.util.BmiCalculator;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ClinicalDataContoller {
	@Autowired
	private PaitentRepository paitentRepository;
	@Autowired
	private ClinicalDataRepository clinicalDataRepository;

	@PostMapping("/clinicals")
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		Patient patient = paitentRepository.findById(request.getPatientId()).get();
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setComponentName(request.getComponentName());
		clinicalData.setComponentValue(request.getComponentValue());
		clinicalData.setPatient(patient);
		return clinicalDataRepository.save(clinicalData);

	}

	@GetMapping("/clinicals/{patientId}/{componentName}")
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
			@PathVariable("componentName") String componentName) {
		if (componentName.equals("bmi")) {
			componentName = "hw";
		}
		List<ClinicalData> clinicalData = clinicalDataRepository
				.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
		List<ClinicalData> dummyData = new ArrayList<>(clinicalData);
		for (ClinicalData data : dummyData) {
			BmiCalculator.calculateBmi(clinicalData, data);
		}
		return clinicalData;

	}

}

package com.nit.clinical.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.clinical.entity.ClinicalData;
import com.nit.clinical.entity.Patient;
import com.nit.clinical.repository.PaitentRepository;
import com.nit.clinical.util.BmiCalculator;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PatientController {
	@Autowired
	private PaitentRepository paitentRepository;
	Map<String, String> filter = new HashMap<String, String>();

	@GetMapping("/paitents")
	public List<Patient> getPatients() {
		return paitentRepository.findAll();
	}

	@GetMapping("/paitents/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		return paitentRepository.findById(id).get();
	}

	@PostMapping("/paitents")
	public Patient savePatient(@RequestBody Patient patient) {
		return paitentRepository.save(patient);
	}

	@GetMapping("/paitents/analise/{id}")
	public Patient analise(@PathVariable("id") int id) {
		Patient patient = paitentRepository.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		List<ClinicalData> dummyData = new ArrayList<>(clinicalData);
		for (ClinicalData data : dummyData) {
			if (filter.containsKey(data.getComponentName())) {
				clinicalData.remove(data);
				continue;
			} else {
				filter.put(data.getComponentName(),null);
			}
			BmiCalculator.calculateBmi(clinicalData, data);
		}
		filter.clear();
		return patient;

	}

	
}

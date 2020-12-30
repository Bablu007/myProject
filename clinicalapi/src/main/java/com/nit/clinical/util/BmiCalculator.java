package com.nit.clinical.util;

import java.util.List;

import com.nit.clinical.entity.ClinicalData;

public class BmiCalculator {
	public static void calculateBmi(List<ClinicalData> clinicalData, ClinicalData data) {
		if (data.getComponentName().equals("hw")) {
			String[] splitData = data.getComponentValue().split("/");
			if (splitData != null && splitData.length > 1) {
				float hieghtInMeter = Float.parseFloat(splitData[0]) * 0.3048f;
				float bmi = Float.parseFloat(splitData[1]) / (hieghtInMeter * hieghtInMeter);
				ClinicalData clinical = new ClinicalData();
				clinical.setComponentName("Bmi");
				clinical.setComponentValue(Float.toString(bmi));
				clinicalData.add(clinical);
			}
		}
	}
}

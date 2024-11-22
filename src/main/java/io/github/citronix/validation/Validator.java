package io.github.citronix.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import io.github.citronix.entity.Field;

@Component
public class Validator {

	public ValidationMessage checkTotalFarmArea(Double fieldsTotalArea, Double farmTotalArea, Double currentFieldArea) {
		Boolean checker = true;
		String message = "";

		if (fieldsTotalArea > farmTotalArea) {
			checker = false;
			message = "The total field area must be less than the farm area.";
		} else if (currentFieldArea >= farmTotalArea / 2) {
			checker = false;
			message = "No field can exceed 50% of the farm's total area.";
		}
		return new ValidationMessage(checker, message);
	}

	public ValidationMessage checkTotalTreesByFieldArea(Field field) {
		int totalTrees = field.getTrees().size();
		Double fieldArea = field.getArea();
		boolean checker = true;
		String message = "";

		int checkTreeCount = (int) (fieldArea / 1000) * 10;

		if (totalTrees >= checkTreeCount) {
			checker = false;
			message = "Each field must have no more than 100 trees per hectare (10 per 1,000 m²).";
		}

		return new ValidationMessage(checker, message);

	}

	public ValidationMessage checkPlantingDate(LocalDate date) {
		int month = date.getMonth().getValue();

		Boolean checker = true;
		String message = "";
		if (month > 5 || month < 3) {
			checker = false;
			message = "Trees can only be planted from March to May, the ideal period.";
		}

		return new ValidationMessage(checker, message);
	}

}

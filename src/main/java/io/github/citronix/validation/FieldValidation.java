package io.github.citronix.validation;

import org.springframework.stereotype.Component;

@Component
public class FieldValidation {

	public ValidationMessage checkTotalArea(Double fieldsTotalArea, Double farmTotalArea, Double currentFieldArea) {
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

}

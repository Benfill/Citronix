package io.github.citronix.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectDate implements ConstraintValidator<CorrectLocalDate, LocalDate> {

	@Override
	public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
		if (date.isAfter(LocalDate.now())) {
			return false;
		}
		return true;
	}

}

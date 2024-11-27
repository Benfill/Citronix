package io.github.citronix.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CorrectDate.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectLocalDate {
	String message() default "The date cannot be in the future.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

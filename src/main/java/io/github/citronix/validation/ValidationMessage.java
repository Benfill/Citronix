package io.github.citronix.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationMessage {

	Boolean checker;
	String message;
}

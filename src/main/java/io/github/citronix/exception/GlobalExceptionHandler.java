package io.github.citronix.exception;

import java.time.LocalDateTime;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CustomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionMessage NotFoundHandler(CustomNotFoundException ex) {
		return new ExceptionMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());

	}

	@ExceptionHandler(value = Validation.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionMessage NotFoundHandler(Validation ex) {
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionMessage HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		return new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionMessage HttpMessageNotReadableExceptionHandler(DataIntegrityViolationException ex) {
		return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionMessage InvalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException ex) {
		return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(value = SQLGrammarException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionMessage SQLGrammarExceptionHandler(SQLGrammarException ex) {
		return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

		ValidationErrorResponse errors = new ValidationErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		errors.setMessage("Validation Failed");

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.addError(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}

}

package io.github.citronix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.citronix.dto.req.FieldReqDto;
import io.github.citronix.dto.resp.FieldRespDto;
import io.github.citronix.entity.Farm;
import io.github.citronix.entity.Field;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.FieldMapper;
import io.github.citronix.repository.IFieldRepository;
import io.github.citronix.service.impl.FarmServiceImpl;
import io.github.citronix.service.impl.FieldServiceImpl;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class FieldServiceTest {

	@Mock
	private IFieldRepository fieldRepository;

	@Mock
	private FarmServiceImpl farmService;

	@Mock
	private Validator validator;

	@Spy
	private FieldMapper mapper;

	@InjectMocks
	private FieldServiceImpl fieldService;

	@Test
	void createField_SuccessfulCreation_ReturnsFieldRespDto() {
		FieldReqDto requestDto = new FieldReqDto();
		requestDto.setFarm_id(1L);
		requestDto.setArea(10.0);

		Farm mockFarm = new Farm();
		mockFarm.setId(1L);
		mockFarm.setTotalArea(100.0);

		Field mockField = new Field();
		mockField.setId(1L);
		mockField.setArea(10.0);

		ValidationMessage validationMessage = new ValidationMessage(true, "");

		when(farmService.getFarmById(1L)).thenReturn(Optional.of(mockFarm));
		when(fieldRepository.sumFieldAreasByFarmId(1L)).thenReturn(50.0);
		when(validator.checkTotalFarmArea(60.0, 100.0, 10.0)).thenReturn(validationMessage);
		when(fieldRepository.countByFarmId(1L)).thenReturn(5L);
		when(mapper.DtoToentity(requestDto)).thenReturn(mockField);
		when(fieldRepository.save(mockField)).thenReturn(mockField);
		when(mapper.entityToDto(mockField)).thenReturn(new FieldRespDto());

		FieldRespDto result = fieldService.createField(requestDto);

		assertNotNull(result);

		verify(farmService).getFarmById(1L);
		verify(fieldRepository).sumFieldAreasByFarmId(1L);
		verify(validator).checkTotalFarmArea(60.0, 100.0, 10.0);
		verify(fieldRepository).countByFarmId(1L);
		verify(fieldRepository).save(mockField);
	}

	@Test
	void createField_ExceedFarmArea_ThrowsValidationException() {
		FieldReqDto requestDto = new FieldReqDto();
		requestDto.setFarm_id(1L);
		requestDto.setArea(60.0);

		Farm mockFarm = new Farm();
		mockFarm.setId(1L);
		mockFarm.setTotalArea(100.0);

		ValidationMessage validationMessage = new ValidationMessage(false, "Exceeded farm area");

		when(farmService.getFarmById(1L)).thenReturn(Optional.of(mockFarm));
		when(fieldRepository.sumFieldAreasByFarmId(1L)).thenReturn(50.0);
		when(validator.checkTotalFarmArea(110.0, 100.0, 60.0)).thenReturn(validationMessage);

		Validation exception = assertThrows(Validation.class, () -> {
			fieldService.createField(requestDto);
		});

		assertEquals("Exceeded farm area", exception.getMessage());

		verify(fieldRepository, never()).save(any());
	}

	@Test
	void createField_ExceedMaxFields_ThrowsValidationException() {
		FieldReqDto requestDto = new FieldReqDto();
		requestDto.setFarm_id(1L);
		requestDto.setArea(10.0);

		Farm mockFarm = new Farm();
		mockFarm.setId(1L);
		mockFarm.setTotalArea(100.0);

		ValidationMessage validationMessage = new ValidationMessage(true, "");

		when(farmService.getFarmById(1L)).thenReturn(Optional.of(mockFarm));
		when(fieldRepository.sumFieldAreasByFarmId(1L)).thenReturn(50.0);
		when(validator.checkTotalFarmArea(60.0, 100.0, 10.0)).thenReturn(validationMessage);
		when(fieldRepository.countByFarmId(1L)).thenReturn(10L);

		Validation exception = assertThrows(Validation.class, () -> {
			fieldService.createField(requestDto);
		});

		assertEquals("A farm can have a maximum of 10 fields.", exception.getMessage());

		verify(fieldRepository, never()).save(any());
	}

}

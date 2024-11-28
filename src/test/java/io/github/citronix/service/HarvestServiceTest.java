package io.github.citronix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.citronix.dto.req.HarvestReqDto;
import io.github.citronix.entity.Field;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.Tree;
import io.github.citronix.entity.enums.Season;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.HarvestMapper;
import io.github.citronix.repository.IHarvestRepository;
import io.github.citronix.service.impl.FieldServiceImpl;
import io.github.citronix.service.impl.HarvestServiceIml;
import io.github.citronix.utils.HarvestUtil;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class HarvestServiceTest {

	@Mock
	private IHarvestRepository repository;

	@Mock
	private FieldServiceImpl fieldService;

	@Mock
	private HarvestUtil harvestUtil;

	@Mock
	private Validator validator;

	@Mock
	private HarvestMapper mapper;

	@InjectMocks
	private HarvestServiceIml harvestService;

	@Test
	void createHarvest_InvalidSeason_ThrowsValidationException() {
		HarvestReqDto requestDto = new HarvestReqDto();
		requestDto.setFieldId(1L);
		requestDto.setYear(2023);
		requestDto.setHarvestDate(LocalDate.of(2023, 6, 15));

		Field mockField = new Field();
		mockField.setId(1L);
		mockField.setTrees(List.of(new Tree(), new Tree()));

		Season mockSeason = Season.SUMMER;
		when(harvestUtil.getSeasonByMonth(requestDto.getHarvestDate())).thenReturn(mockSeason);

		when(fieldService.getFieldById(1L)).thenReturn(Optional.of(mockField));

		List<Harvest> existingHarvests = Collections.emptyList();
		when(repository.findByYearAndFieldId(2023, 1L)).thenReturn(existingHarvests);

		ValidationMessage seasonValidationFailed = new ValidationMessage(false, "Invalid harvest season");
		when(validator.checkHarvestSeason(existingHarvests, mockSeason)).thenReturn(seasonValidationFailed);

		Validation exception = assertThrows(Validation.class, () -> {
			harvestService.createHarvest(requestDto);
		});

		assertEquals("Invalid harvest season", exception.getMessage());

		verify(fieldService).getFieldById(1L);
		verify(harvestUtil).getSeasonByMonth(requestDto.getHarvestDate());
		verify(repository).findByYearAndFieldId(2023, 1L);
		verify(validator).checkHarvestSeason(existingHarvests, mockSeason);
		verify(repository, never()).save(any());
	}
}
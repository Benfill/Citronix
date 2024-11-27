package io.github.citronix.validation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.enums.Season;

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
		int month = date.getMonthValue();

		Boolean checker = true;
		String message = "";
		if (month > 5 || month < 3) {
			checker = false;
			message = "Trees can only be planted from March to May, the ideal period.";
		}

		return new ValidationMessage(checker, message);
	}

	public ValidationMessage checkHarvestSeason(List<Harvest> harvests, Season season) {
		Boolean checker = true;
		String message = "";

		List<Harvest> harvestsChecker = harvests.stream().filter(h -> h.getSeason() == season)
				.collect(Collectors.toList());
		if (!harvestsChecker.isEmpty()) {
			checker = false;
			message = "A field can have only one harvest per season, about every 3 months.";
		}

		return new ValidationMessage(checker, message);
	}

	public ValidationMessage checkHarvestYear(LocalDate harvestDate, Integer year) {
		int yearDate = harvestDate.getYear();
		Boolean checker = true;
		String message = "";

		if (yearDate != year) {
			checker = false;
			message = "The provided year does not match the year in the date.";
		}

		return new ValidationMessage(checker, message);
	}

	public ValidationMessage validateSaleQuantity(Harvest harvest, Double saleQuantity) {
		Boolean checker = true;
		String message = "";
		Double harvestQuantity = harvest.getTotalQuantity();
		double salesQuantityTotal = harvest.getSales().stream().mapToDouble(s -> s.getQuantity()).sum();
		if (saleQuantity > harvestQuantity || saleQuantity + salesQuantityTotal > harvestQuantity) {
			checker = false;
			message = "Sale quantity exceeds harvest quantity.";
		}
		return new ValidationMessage(checker, message);
	}

}

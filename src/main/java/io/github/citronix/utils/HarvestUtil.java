package io.github.citronix.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.enums.Season;

@Component
public class HarvestUtil {

	public Season getSeasonByMonth(LocalDate harvestDate) {
		int month = harvestDate.getMonthValue();

		if ((month >= 6 && month < 9)) {
			return Season.SUMMER;
		} else if (month >= 9 && month < 12) {
			return Season.AUTUMN;
		} else if (month == 12 || month < 3) {
			return Season.WINTER;
		} else {
			return Season.SPRING;
		}
	}

	public Double getTotalQuantity(Field field) {
		return field.getTrees().stream().mapToDouble(t -> t.getSeasonalProductivity()).sum();
	}
}

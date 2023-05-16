package ru.neoflex.vacationpaycalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;
import ru.neoflex.vacationpaycalculator.service.VacationPayCalculatorService;
import ru.neoflex.vacationpaycalculator.service.VacationPayCalculatorServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationPayCalculatorServiceImplTest {

	private final VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorServiceImpl();

	@Test
	public void calculateVacationPay_WithoutVacationStartDate_CalculateVacationPay() {
		double expected = 30000 / 29.3 * 14;

		VacationPayRequest request = new VacationPayRequest(30000, 14, null);
		VacationPayResponse response = vacationPayCalculatorService.calculateVacationPay(request);

		assertEquals(response.getVacationPay(), expected);
	}

	@Test
	public void calculateVacationPay_WithVacationStartDate_CalculateVacationPay() {
		double expected = 30000 / 29.3 * 9;

		VacationPayRequest request = new VacationPayRequest(30000, 14, LocalDate.of(2023, 3, 6));
		VacationPayResponse response = vacationPayCalculatorService.calculateVacationPay(request);

		assertEquals(response.getVacationPay(), expected);
	}
}

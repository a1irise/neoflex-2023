package ru.neoflex.vacationpaycalculator.service;

import org.springframework.stereotype.Service;
import ru.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import ru.neoflex.vacationpaycalculator.dto.VacationPayResponse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {

    private static final double AVERAGE_NUMBER_OF_DAYS_PER_MONTH = 29.3;

    private static final List<DayOfWeek> WEEKENDS = List.of(DayOfWeek.SATURDAY , DayOfWeek.SUNDAY);
    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private static final List<LocalDate> HOLIDAYS = List.of(
            LocalDate.of(CURRENT_YEAR, 1, 1),
            LocalDate.of(CURRENT_YEAR, 1, 2),
            LocalDate.of(CURRENT_YEAR, 1, 3),
            LocalDate.of(CURRENT_YEAR, 1, 4),
            LocalDate.of(CURRENT_YEAR, 1, 5),
            LocalDate.of(CURRENT_YEAR, 1, 6),
            LocalDate.of(CURRENT_YEAR, 2, 23),
            LocalDate.of(CURRENT_YEAR, 2, 24),
            LocalDate.of(CURRENT_YEAR, 3, 8),
            LocalDate.of(CURRENT_YEAR, 5, 1),
            LocalDate.of(CURRENT_YEAR, 5, 8),
            LocalDate.of(CURRENT_YEAR, 5, 9),
            LocalDate.of(CURRENT_YEAR, 6, 12),
            LocalDate.of(CURRENT_YEAR, 11, 6));

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest request) {
        double vacationPay;
        if (request.getVacationStartDate() != null) {
            vacationPay = request.getAverageMonthlySalary() / AVERAGE_NUMBER_OF_DAYS_PER_MONTH
                    * getVacationDurationInDaysExcludingHolidays(request);
        } else {
            vacationPay = request.getAverageMonthlySalary() / AVERAGE_NUMBER_OF_DAYS_PER_MONTH
                    * request.getVacationDurationInDays();
        }
        return new VacationPayResponse(vacationPay);
    }

    private long getVacationDurationInDaysExcludingHolidays(VacationPayRequest request) {
        LocalDate startDate = request.getVacationStartDate();
        LocalDate endDate = startDate.plusDays(request.getVacationDurationInDays());

        return startDate.datesUntil(endDate)
                .filter(date -> !(HOLIDAYS.contains(date) || WEEKENDS.contains(date.getDayOfWeek())))
                .count();
    }
}

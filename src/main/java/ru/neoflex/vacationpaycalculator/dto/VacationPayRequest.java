package ru.neoflex.vacationpaycalculator.dto;

import java.time.LocalDate;

public class VacationPayRequest {

    private final double averageMonthlySalary;
    private final int vacationDurationInDays;
    private final LocalDate vacationStartDate;

    public VacationPayRequest(double averageMonthlySalary, int vacationDurationInDays, LocalDate vacationStartDate) {
        this.averageMonthlySalary = averageMonthlySalary;
        this.vacationDurationInDays = vacationDurationInDays;
        this.vacationStartDate = vacationStartDate;
    }

    public double getAverageMonthlySalary() {
        return averageMonthlySalary;
    }

    public int getVacationDurationInDays() {
        return vacationDurationInDays;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }
}

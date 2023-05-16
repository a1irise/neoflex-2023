package ru.neoflex.vacationpaycalculator.dto;

public class VacationPayResponse {

    private final double vacationPay;

    public VacationPayResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    public double getVacationPay() {
        return vacationPay;
    }
}

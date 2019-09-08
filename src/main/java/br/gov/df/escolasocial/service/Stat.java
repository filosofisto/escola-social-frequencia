package br.gov.df.escolasocial.service;

public class Stat {

    private int quantityReccessOrHoliday;
    private int quantityMonthYear;
    private int quantityMatriculas;
    private int records;

    public Stat(int quantityReccessOrHoliday, int quantityMonthYear, int quantityMatriculas) {
        this.quantityReccessOrHoliday = quantityReccessOrHoliday;
        this.quantityMonthYear = quantityMonthYear;
        this.quantityMatriculas = quantityMatriculas;
    }

    public void inc() {
        records++;
    }

    public int getRecords() {
        return records;
    }

    public int getQuantityReccessOrHoliday() {
        return quantityReccessOrHoliday;
    }

    public int getQuantityMonthYear() {
        return quantityMonthYear;
    }

    public int getQuantityMatriculas() {
        return quantityMatriculas;
    }
}

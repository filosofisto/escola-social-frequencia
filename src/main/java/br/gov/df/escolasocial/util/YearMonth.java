package br.gov.df.escolasocial.util;

import java.util.Objects;

public class YearMonth implements Cloneable {

    private Integer year;
    private Integer month;

    public YearMonth(Integer year, Integer month) {
        this.year = year;
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearMonth yearMonth = (YearMonth) o;
        return year.equals(yearMonth.year) &&
                month.equals(yearMonth.month);
    }

    public void decMonth() {
        month--;
        if (month == 0) {
            year--;
            month = 12;
        }
    }

    public void incMonth() {
        month++;
        if (month > 12) {
            month = 1;
            year++;
        }
    }

    public YearMonth lessOneMonth() {
        YearMonth clone = clone();
        clone.decMonth();

        return clone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }

    public YearMonth clone() {
        try {
            return (YearMonth) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

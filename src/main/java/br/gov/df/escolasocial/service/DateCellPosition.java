package br.gov.df.escolasocial.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCellPosition {

    private Date date;

    private int column;

    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DateCellPosition(Date date, int column) {
        this.date = date;
        this.column = column;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "DateCellPosition{" +
                "date=" + dateFormat.format(date) +
                ", column=" + column +
                '}';
    }
}

package br.gov.df.escolasocial.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateCellPositionContainer {

    private List<DateCellPosition> list;

    public DateCellPositionContainer() {
        this.list = new ArrayList<>();
    }

    public void add(Date date, int pos) {
        list.add(new DateCellPosition(date, pos));
    }

    public List<DateCellPosition> list() {
        return list;
    }

    @Override
    public String toString() {
        return "DateCellPositionContainer{" +
                "list=" + list +
                '}';
    }

    public int count() {
        return list.size();
    }
}

package br.gov.df.escolasocial.exception;

import br.gov.df.escolasocial.service.DateCellPosition;

public class InvalidFrequencyData extends Exception {

    public InvalidFrequencyData(int row, DateCellPosition dateCellPosition) {
        super("Dado de frequencia invalido na linha " + row + " e colunas [" + (dateCellPosition.getColumn()-1) + "/" +
                dateCellPosition.getColumn() + "]");
    }
}

package com.intech.mysudoku.model.javafx;

import com.intech.mysudoku.tools.Board;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class BoardPane extends GridPane {

    private static Board board;
    private static int cellValueCount = 0;
    private List<IntField> intFields = new ArrayList<>();


    public List<IntField> getIntFields()
    {
        return this.intFields;
    }

    public void setIntFields(List<IntField> intFields)
    {
        this.intFields = intFields;
    }

    public int getCellValueCount() {
        return cellValueCount;
    }

    public void setCellValueCount(int cellValueCount) {
        BoardPane.cellValueCount = cellValueCount;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {

        BoardPane.board = board;

    }

    public BoardPane() {
        super();
    }

}

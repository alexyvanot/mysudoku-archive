package com.intech.mysudoku.model.javafx;

import com.intech.mysudoku.tools.Board;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class BoardPane extends GridPane {

    private static Board board;

    private static int cellValueCount = 0;



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

        this.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {



            }

        });
    }

}

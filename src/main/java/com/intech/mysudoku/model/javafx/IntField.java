package com.intech.mysudoku.model.javafx;

import com.intech.mysudoku.controllers.GameScreenController;
import com.intech.mysudoku.tools.Board;
import com.intech.mysudoku.tools.Cell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class IntField extends TextField {

    BoardPane boardPane;
    GameScreenController gameScreenController;

    final private IntegerProperty value;
    final private int minValue;
    final private int maxValue;
    private Cell cell;
    private boolean initialCell = false; // True si c'est une case initiale du puzzle (non éditable)

    public BoardPane getBoardPane() {
        return boardPane;
    }
    public void setBoardPane(BoardPane boardPane) {
        this.boardPane = boardPane;
    }
    public int  getValue()                 { return value.getValue(); }
    public void setValue(int newValue)     { value.setValue(newValue); }
    public IntegerProperty valueProperty() { return value; }
    
    public Cell getCell() { return cell; }
    public boolean isInitialCell() { return initialCell; }
    public void setInitialCell(boolean initial) { this.initialCell = initial; }


    public IntField(int initialValue, int minValue, int maxValue, Cell cell, BoardPane pane, GameScreenController gsc) {
        gameScreenController = gsc;
        if (minValue > maxValue)
            throw new IllegalArgumentException(
                    "IntField min value " + minValue + " greater than max value " + maxValue
            );
        if (!((minValue <= initialValue) && (initialValue <= maxValue)))
            throw new IllegalArgumentException(
                    "IntField initialValue " + initialValue + " not between " + minValue + " and " + maxValue
            );

        // initialize the field values.
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = new SimpleIntegerProperty(initialValue);
        this.cell = cell;
        this.boardPane = pane;
        setText(initialValue + "");

        final IntField intField = this;

        if (initialValue != 0) {

            boardPane.setCellValueCount(boardPane.getCellValueCount() + 1);
            System.out.println("boardpane cellValueCount: " + boardPane.getCellValueCount());

        }

        // make sure the value property is clamped to the required range
        // and update the field's text to be in sync with the value.
        value.addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue == null) {
                    intField.setText("");
                } else {
                    if (newValue.intValue() < intField.minValue) {
                        value.setValue(intField.minValue);
                        return;
                    }

                    if (newValue.intValue() > intField.maxValue) {
                        value.setValue(intField.maxValue);
                        return;
                    }

                    if (newValue.intValue() == 0 && (textProperty().get() == null || "".equals(textProperty().get()))) {
                        // no action required, text property is already blank, we don't need to set it to 0.
                        boardPane.setCellValueCount(boardPane.getCellValueCount() - 1);
                        cell.setValue(0);
                        System.out.println("boardpane cellValueCount: " + boardPane.getCellValueCount());
                    } else {
                        intField.setText(newValue.toString());
                        boardPane.setCellValueCount(boardPane.getCellValueCount() + 1);
                        System.out.println("boardpane cellValueCount: " + boardPane.getCellValueCount());
                        if (boardPane.getCellValueCount() == 81) {
                            System.out.println("grid completed : checking");
                            if (gameScreenController.verify()) {
                                try {
                                    gameScreenController.handleShowWinScreen();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        });

        // restrict key input to numerals.
        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if(intField.minValue<0) {
                    if (!"-123456789".contains(keyEvent.getCharacter())) {
                        keyEvent.consume();
                    }
                }
                else {
                    if (!"123456789".contains(keyEvent.getCharacter())) {
                        keyEvent.consume();
                    } else {
                        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                            cell.setValue(0);
                        } else {
                            if (cell.getValue() != 0) {
                                keyEvent.consume();
                                System.out.println("keyevent consumed");
                            } else {
                                cell.setValue(Integer.parseInt(keyEvent.getCharacter()));
                            }
                        }
                    }
                }
                System.out.println("Cell value: " + cell.getValue());
            }
        });

        // ensure any entered values lie inside the required range.
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue == null || "".equals(newValue) || (intField.minValue<0 && "-".equals(newValue))) {
                    value.setValue(0);
                    return;
                }

                final int intValue = Integer.parseInt(newValue);

                if (intField.minValue > intValue || intValue > intField.maxValue) {
                    textProperty().setValue(oldValue);
                    //cell.setValue(Integer.parseInt(oldValue));
                }

                value.set(Integer.parseInt(textProperty().get()));
                //cell.setValue(Integer.parseInt(textProperty().get()));
            }
        });
    }
}

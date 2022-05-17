package com.intech.mysudoku.application;

import com.intech.mysudoku.controllers.TitleScreenController;
import com.intech.mysudoku.tools.Board;
import com.intech.mysudoku.tools.Creator;
import com.intech.mysudoku.tools.Level;
import com.intech.mysudoku.tools.Solver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MySudokuApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	/**
    	 * Sudoku invocator
    	 */
    	System.out.println("Loading application...");
    	Creator sudoku = new Creator();
		sudoku.create(Level.EASY);
		Board sudokuBoard = sudoku.getBoard();
		System.out.println(sudokuBoard);
		Solver solveThisSudoku = new Solver();
		solveThisSudoku.setBoard(sudokuBoard).solve(sudokuBoard.getCells(), 1);
		System.out.println();
		System.out.println(sudokuBoard);
    	
    	/* "Deprecated"
    	int N = 9, K = 20;
		Sudoku sudoku = new Sudoku(N, K);
		sudoku.fillValues();
		sudoku.printSudoku(); // Show in console 
		*/
		
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
            Parent root = loader.load();
            TitleScreenController titleScreenController = loader.getController();
            Scene scene = new Scene(root);
            titleScreenController.setStartScene(scene);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

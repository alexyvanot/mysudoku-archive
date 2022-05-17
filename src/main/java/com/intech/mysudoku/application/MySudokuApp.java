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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MySudokuApp extends Application {

	private Stage primaryStage;
	private Parent root;
	
	@Override
	public void init() throws Exception {
		super.init();
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	this.primaryStage = primaryStage;
    	initRootLayout();
        
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
            Parent root = loader.load();
            TitleScreenController titleScreenController = loader.getController();
            Scene scene = new Scene(root);
            titleScreenController.setStartScene(scene);
            this.primaryStage.setTitle("MySudoku");
            primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void invocateSudoku() {
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
		
    }
    
    @Override
    public void stop() throws Exception {
    	super.stop();
    }
    
    public static void main(String[] args) {
    	Application.launch(args);
    }



}

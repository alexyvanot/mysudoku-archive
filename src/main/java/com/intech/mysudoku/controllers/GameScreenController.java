package com.intech.mysudoku.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.intech.mysudoku.model.javafx.BoardPane;
import com.intech.mysudoku.model.javafx.IntField;
import com.intech.mysudoku.tools.Board;
import com.intech.mysudoku.tools.Cell;
import com.intech.mysudoku.tools.Creator;
import com.intech.mysudoku.tools.Level;
import com.intech.mysudoku.tools.Solver;
import com.intech.mysudoku.tools.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameScreenController implements Initializable {

    Scene scene;
    TitleScreenController titleScreenController;
    WinScreenController winScreenController;
    Stage stage;

    Level difficulty;
    Creator creator;
    Board board;
    BoardPane boardPane;
    Solver solver;
    boolean clicked = false;
    static int N = 9;


    @FXML
    AnchorPane anchorPane;
    @FXML
    BoardPane grid;
    @FXML
    Label titleLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Text difficultyText;
    @FXML
    Label chronoLabel;
    @FXML
    Text chronoText;
    @FXML
    Button exitGameButton;
    @FXML
    Button giveUpButton;
    
    Timer time = new Timer("00:00:00");
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
    	time.oneSecondPassed();
    	chronoText.setText(time.getCurrentTime());
    }));


  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       chronoText.setText(time.getCurrentTime());

        creator = new Creator();
        difficulty = TitleScreenController.getDifficulty();
        System.out.println("difficulty : " + difficulty);
        difficultyText.setText(difficulty.toString());
        board = creator.create(difficulty);
        grid.setBoard(board);
        grid.setPadding(new Insets(10,10,10,10));
        for (Cell cell : grid.getBoard().getCells()) {
            String txt = cell.getValue().toString();
            //TextField t = new TextField();
            IntField t = new IntField(cell.getValue(), 0, 9, cell, grid, this);
            Font font = new Font("SansSerif", 25);
            t.setFont(font);
            t.setAlignment(Pos.CENTER);
            
            if(txt.equals("0")) {
                t.setEditable(true);
                t.setStyle("-fx-background-color: black,"
                		+ "-fx-control-inner-background;"
                		+ "-fx-background-insets: 0, 2;"
                		+ "-fx-padding: 2;"
                		);
                /*
                t.setStyle(""
                		+ "-fx-background-color: #0e0d0c,"
                		+ "-fx-control-inner-background;"
                		+ "-fx-grid-lines-visible: true;"
                		+ "-fx-background-insets: 0, 4;"
                		+ "-fx-padding: 2;"
                		);
                */
                t.setText("");
                t.setFont(Font.font("SansSerif", FontWeight.NORMAL, 26));
            
            } else {
            	t.setStyle("-fx-background-color: black,"
                		+ "-fx-control-inner-background;"
                		+ "-fx-background-insets: 0, 2;"
                		+ "-fx-padding: 2;"
                		);
            	/*
            	t.setStyle(""
                		+ "-fx-background-color: #0e0d0c,"
                		+ "-fx-control-inner-background;"
                		+ "-fx-grid-lines-visible: true;"
                		+ "-fx-background-insets: 0, 4;"
                		+ "-fx-padding: 2;"
                		);
                */
                t.setEditable(false);
                t.setFont(Font.font("SansSerif", FontWeight.BOLD, 25));
            }
            
            t.setPrefWidth(70);
            t.setPrefHeight(70);
            
            grid.add(t, cell.getColumn(), cell.getRow());
            grid.getIntFields().add(t);
        
        }


        chronoText.setText(time.getCurrentTime());
        chronoText.setStyle("-fx-text-color: blue");
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

    public void setWinScreenController(WinScreenController winScreenController) {
        this.winScreenController = winScreenController;
    }

    public void handleExitGame(ActionEvent event) throws IOException {
        boardPane = new BoardPane();
        boardPane.setCellValueCount(0);
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleResolve() throws IOException {
        boardPane = new BoardPane();
        boardPane.setCellValueCount(0);
        if (clicked) {
            return;
        }
        clicked = true;
        
        System.out.println("Solving this sudoku board:");
        System.out.println(board);
        System.out.println();
        solver = new Solver();
        solver.setBoard(board).solve(board.getCells(), 1);
        try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        grid.setBoard(board);
        System.out.println(board);
        System.out.println("get");
        System.out.println(grid.getBoard());
        
        
        for (Cell cell : grid.getBoard().getCells()) {
            IntField t = new IntField(cell.getValue(), 0, 9, cell, grid, this);
            Font font = new Font("SansSerif", 25);
            t.setFont(font);
            t.setAlignment(Pos.CENTER);
            t.setStyle("-fx-background-color: black,"
            		+ "-fx-control-inner-background;"
            		+ "-fx-background-insets: 0, 2;"
            		+ "-fx-padding: 2;"
            		);
            t.setEditable(false);
            t.setPrefWidth(70);
            t.setPrefHeight(70);
            
            grid.add(t, cell.getColumn(), cell.getRow());
            grid.getIntFields().add(t);
            
        }
        
        System.out.println(grid);
        handleShowWinScreen();
    }

    public void handleShowWinScreen() throws IOException {
        stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/winScreen.fxml"));
        Parent root = loader.load();
        WinScreenController winScreenController = loader.getController();
        winScreenController.setGameScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public BoardPane getBoardPane() {
        return boardPane;
    }

    static boolean isinRange(int[][] board) {
        // Traverse board[][] array
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {

                // Check if board[i][j]
                // lies in the range
                if (board[i][j] <= 0 ||
                        board[i][j] > 9)
                {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValidSudoku(int[][] board) {
        if (isinRange(board) == false) {
            return false;
        }

        boolean[] unique = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(unique, false);
            for (int j = 0; j < N; j++) {
                int Z = board[i][j];

                if (unique[Z]) {
                    return false;
                }
                unique[Z] = true;
            }
        }

        for (int i = 0; i < N - 2; i +=3) {
            for (int j = 0; j < N - 2; j += 3) {
                Arrays.fill(unique, false);

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int X = i + k;
                        int Y = j + l;
                        int Z = board[X][Y];

                        if (unique[Z]) {
                            return false;
                        }
                        unique[Z] = true;
                    }
                }
            }
        }
        return true;
    }

    public boolean verify() {
        BoardPane board = getBoardPane();
        Board b = board.getBoard();
        int[][] gridint = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; i < 9; i++) {
                Cell c = b.getCell(i, j);
                gridint[i][j] = c.getValue();
            }
        }
        return isValidSudoku(gridint);
    }




}

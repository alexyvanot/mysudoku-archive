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
    boolean gaveUp = false;
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
            IntField t = new IntField(cell.getValue(), 0, 9, cell, grid, this);
            t.setAlignment(Pos.CENTER);
            
            // Calculer les bordures pour les carrés 3x3
            String borderStyle = getBorderStyle(cell.getRow(), cell.getColumn());
            
            if(txt.equals("0")) {
                // Case éditable - fond légèrement différent
                t.setEditable(true);
                t.setStyle("-fx-background-color: #1a1a2e, #16213e;"
                        + "-fx-background-insets: 0, 2;"
                        + "-fx-text-fill: #4fc3f7;"
                        + borderStyle);
                t.setText("");
                t.setFont(Font.font("SansSerif", FontWeight.NORMAL, 26));
                t.setInitialCell(false);
            } else {
                // Case non-éditable - chiffre de base en gras, fond plus clair
                t.setStyle("-fx-background-color: #2d2d44, #252540;"
                        + "-fx-background-insets: 0, 2;"
                        + "-fx-text-fill: #ffffff;"
                        + borderStyle);
                t.setEditable(false);
                t.setFont(Font.font("SansSerif", FontWeight.BOLD, 25));
                t.setInitialCell(true);
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
        gaveUp = true; // Marquer comme abandon
        
        // Arrêter le timer
        timeline.stop();
        
        System.out.println("Solving this sudoku board:");
        System.out.println(board);
        System.out.println();
        
        // Sauvegarder les valeurs entrées par l'utilisateur AVANT de restaurer
        java.util.Map<Cell, Integer> userValues = new java.util.HashMap<>();
        for (Cell cell : board.getCells()) {
            userValues.put(cell, cell.getValue());
        }
        
        // Restaurer la grille originale (annuler les entrées de l'utilisateur)
        board.load();
        System.out.println("After load (original puzzle):");
        System.out.println(board);
        
        solver = new Solver();
        // Utiliser uniquement les cellules vides pour la résolution
        java.util.List<Cell> blankCells = Creator.getBlankCells(board);
        solver.setBoard(board).solve(blankCells, 1);
        
        grid.setBoard(board);
        System.out.println("After solve:");
        System.out.println(board);
        
        // Effacer la grille actuelle et afficher la solution
        grid.getChildren().clear();
        grid.getIntFields().clear();
        
        for (Cell cell : grid.getBoard().getCells()) {
            IntField t = new IntField(cell.getValue(), 0, 9, cell, grid, this);
            t.setAlignment(Pos.CENTER);
            t.setEditable(false);
            t.setPrefWidth(70);
            t.setPrefHeight(70);
            
            // Calculer les bordures pour les carrés 3x3
            String borderStyle = getBorderStyle(cell.getRow(), cell.getColumn());
            
            // Vérifier si c'était une case initiale du puzzle
            boolean isInitialCell = cell.getSavedValue() != 0;
            
            // Récupérer ce que l'utilisateur avait mis
            int userValue = userValues.get(cell);
            int correctValue = cell.getValue(); // La solution
            int originalValue = cell.getSavedValue(); // La valeur initiale du puzzle
            
            String textColor;
            FontWeight fontWeight = FontWeight.NORMAL;
            
            if (isInitialCell) {
                // Case initiale du puzzle - texte blanc en gras
                textColor = "#ffffff";
                fontWeight = FontWeight.BOLD;
            } else if (userValue == correctValue && userValue != 0) {
                // L'utilisateur avait mis le bon chiffre - VERT
                textColor = "#2ecc71";
                fontWeight = FontWeight.BOLD;
            } else if (userValue == 0) {
                // Case vide complétée par le programme - ROUGE
                textColor = "#e74c3c";
            } else {
                // L'utilisateur s'était trompé, corrigé par le programme - ORANGE
                textColor = "#f39c12";
            }
            
            // Fond selon si c'était une case initiale ou non
            String bgColor = isInitialCell ? "#2d2d44, #252540" : "#1a1a2e, #16213e";
            
            t.setStyle("-fx-background-color: " + bgColor + ";"
                    + "-fx-background-insets: 0, 2;"
                    + "-fx-text-fill: " + textColor + ";"
                    + borderStyle);
            t.setFont(Font.font("SansSerif", fontWeight, 25));
            
            grid.add(t, cell.getColumn(), cell.getRow());
            grid.getIntFields().add(t);
        }
        
        System.out.println(grid);
        
        // Changer le texte du bouton Give Up pour continuer vers l'écran de fin
        giveUpButton.setText("Continue");
        giveUpButton.setOnAction(e -> {
            try {
                handleShowWinScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void handleShowWinScreen() throws IOException {
        stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/winScreen.fxml"));
        Parent root = loader.load();
        WinScreenController winScreenController = loader.getController();
        winScreenController.setGameScreenController(this);
        winScreenController.setGaveUp(gaveUp);
        winScreenController.setFinalTime(time.getCurrentTime());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public boolean isGaveUp() {
        return gaveUp;
    }
    
    /**
     * Génère le style de bordure pour créer les lignes épaisses des carrés 3x3
     */
    private String getBorderStyle(int row, int col) {
        int top = 1, right = 1, bottom = 1, left = 1;
        
        // Bordures épaisses pour les limites des blocs 3x3
        if (row % 3 == 0) top = 3;      // Haut du bloc
        if (row % 3 == 2) bottom = 3;   // Bas du bloc  
        if (col % 3 == 0) left = 3;     // Gauche du bloc
        if (col % 3 == 2) right = 3;    // Droite du bloc
        
        // Bordures extérieures encore plus épaisses
        if (row == 0) top = 4;
        if (row == 8) bottom = 4;
        if (col == 0) left = 4;
        if (col == 8) right = 4;
        
        return "-fx-border-color: #6c63ff;"
             + "-fx-border-width: " + top + " " + right + " " + bottom + " " + left + ";";
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

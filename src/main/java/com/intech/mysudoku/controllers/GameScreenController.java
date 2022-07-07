package com.intech.mysudoku.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.intech.mysudoku.model.javafx.BoardPane;
import com.intech.mysudoku.model.javafx.IntField;
import com.intech.mysudoku.tools.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    Stage stage;

    Level difficulty;
    Creator creator;
    Board board;
    BoardPane boardPane;

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

        for (Cell cell : grid.getBoard().getCells()) {
            String txt = cell.getValue().toString();
            //TextField t = new TextField();

            IntField t = new IntField(cell.getValue(), 0, 9, cell, grid);
            Font font = new Font("SansSerif", 25);
            t.setFont(font);
            t.setAlignment(Pos.CENTER);
            t.setStyle("-fx-background-color: #0e0d0c, -fx-control-inner-background;-fx-grid-lines-visible: true; -fx-background-insets: 0, 4; -fx-padding: 2;");
            if(txt.equals("0")) {
                t.setEditable(true);
                t.setText("");
                t.setFont(Font.font("SansSerif", FontWeight.BOLD, 26));


            } else {
                t.setEditable(false);


             }
            t.setPrefWidth(70);
            t.setPrefHeight(70);
            grid.add(t,
                    cell.getColumn(),
                    cell.getRow()
            );
            grid.getIntFields().add(t);
        }


        chronoText.setText(time.getCurrentTime());

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

    public void handleExitGame(ActionEvent event) throws IOException {
        boardPane = new BoardPane();
        boardPane.setCellValueCount(0);
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
        Parent root = loader.load();
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();
    }



}

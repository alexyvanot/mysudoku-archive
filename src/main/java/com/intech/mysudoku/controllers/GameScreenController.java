package com.intech.mysudoku.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.intech.mysudoku.model.javafx.IntField;
import com.intech.mysudoku.tools.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;


public class GameScreenController implements Initializable {

    Scene scene;
    TitleScreenController titleScreenController;
    Stage stage;
    Level difficulty;
    Creator creator;
    Board board;

    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane grid;
    @FXML
    Label titleLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Label chronoLabel;
    @FXML
    Button exitGameButton;
    @FXML
    StackPane st1;
    @FXML
    ImageView img1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creator = new Creator();
        difficulty = titleScreenController.getDifficulty();
        System.out.println("difficulty : " + difficulty);
        board = creator.create(difficulty);

        for (Cell cell : board.getCells()) {
            String txt = cell.getValue().toString();
            //TextField t = new TextField();
            IntField t = new IntField(cell.getValue(), 0, 9);
            Font font = new Font("SansSerif", 25);
            t.setFont(font);
            t.setAlignment(Pos.CENTER);
            t.setStyle("-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");

            if(txt.equals("0")) {
                t.setEditable(true);
                t.setText("");

            } else {
                t.setEditable(false);
            }
            t.setPrefWidth(70);
            t.setPrefHeight(70);
            grid.add(
                    t,
                    cell.getColumn(),
                    cell.getRow()
            );

        }

    }


    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

    public void handleShowMainMenu(ActionEvent event) throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
        Parent root = loader.load();
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();
    }


}

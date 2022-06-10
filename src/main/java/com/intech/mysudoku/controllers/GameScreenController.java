package com.intech.mysudoku.controllers;

import com.intech.mysudoku.tools.Board;
import com.intech.mysudoku.tools.Cell;
import com.intech.mysudoku.tools.Creator;
import com.intech.mysudoku.tools.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creator = new Creator();
        difficulty = titleScreenController.getDifficulty();
        System.out.println("difficulty : " + difficulty);
        board = creator.create(difficulty);
        for (Cell cell : board.getCells()) {
            String txt = String.valueOf(cell.getValue());
            System.out.println("cell : " + txt);
            grid.add(
                    txt.equals("0") ? new Label(" ") : new Label(txt),
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

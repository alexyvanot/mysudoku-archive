package com.intech.mysudoku.controllers;

import com.intech.mysudoku.model.javafx.BoardPane;
import com.intech.mysudoku.tools.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WinScreenController implements Initializable {

    Scene scene;
    Stage stage;
    TitleScreenController titleScreenController;
    GameScreenController gameScreenController;
    BoardPane boardPane;
    Level difficulty;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label titleLabel;
    @FXML
    Label subtitleLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Text difficultyText;
    @FXML
    Label chronoLabel;
    @FXML
    Text chronoText;
    @FXML
    Button replayButton;
    @FXML
    Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardPane = new BoardPane();
        boardPane.setCellValueCount(0);
        difficulty = TitleScreenController.getDifficulty();
        difficultyText.setText(difficulty.toString());
    }

    public void handleShowMainMenu(ActionEvent event) throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/titleScreen.fxml"));
        Parent root = loader.load();
        TitleScreenController titleScreenController = loader.getController();
        titleScreenController.setWinScreenController(this);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleReplayGame() throws IOException {
        this.stage = (Stage) anchorPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/gameScreen.fxml"));
        Parent root = loader.load();
        GameScreenController gameScreenController = loader.getController();
        gameScreenController.setWinScreenController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setTitleScreenController (TitleScreenController titleScreenController) {
        this.titleScreenController = titleScreenController;
    }

    public void setGameScreenController(GameScreenController gameScreenController) {
        this.gameScreenController = gameScreenController;
    }
}

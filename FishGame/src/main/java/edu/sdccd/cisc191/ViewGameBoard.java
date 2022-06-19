package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import java.awt.*;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private Canvas gameCanvas;
    private Group gameGroup;
    private Scene gameScene;
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;
    private Button restartGame;


    public static void main(String[] args)
    {
        Application.launch();
    }

    public void updateHeader()
    {
        fishRemaining.setText("Fish: " + controller.modelGameBoard.getFishRemaining());
        guessesRemaining.setText("Bait: " + controller.modelGameBoard.getGuessesRemaining());
        if(controller.fishWin())
        {
            message.setText("Fishes win!");
        }
        else if(controller.playerWins())
        {
            message.setText("YOU WIN!");
        }
        else
        {
            message.setText("Find the fish!");
        }
    }

    public void restart()
    {
        gameGroup.getChildren().clear();
        newGame();
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        gameCanvas = new Canvas();
        gameGroup = new Group();
        newGame();
        gameScene = new Scene(gameGroup);
        stage.setTitle("Gone Fishing!!");
        stage.setScene(gameScene);
        stage.show();
    }
    private void newGame() {
        controller = new ControllerGameBoard();
        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();


        restartGame = new Button();
        restartGame.setPadding(new Insets(25));
        restartGame.setText("Restart");
        restartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restart();
            }
        });

        HBox headerRow = new HBox();
        headerRow.getChildren().add(fishRemaining);
        headerRow.getChildren().add(guessesRemaining);
        headerRow.getChildren().add(message);
        headerRow.getChildren().add(restartGame);
        updateHeader();

        VBox colContainer = new VBox(6);
        colContainer.getChildren().add(headerRow);
        for (int row = 0; row < ModelGameBoard.DIMENSION; row++) {

            HBox rowContainer = new HBox(6);
            for (int col = 0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row, col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e ->
                {
                    controller.makeGuess(finalRow, finalCol);
                    if (!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });

                rowContainer.getChildren().add(button);
            }

            colContainer.getChildren().add(rowContainer);
        }

        gameGroup.getChildren().add(colContainer);
    }
}

package edu.sdccd.cisc191;

import javafx.scene.control.Button;

/**
 * Extends the basic JavaFX Button with game functionality
 */
public class GameBoardButton extends Button {
    private int row;
    private int col;
    private boolean hasFish;
    private boolean isGuessed;

    public GameBoardButton(int row, int col, boolean hasFish)
    {
        this.row = row;
        this.col = col;
        this.hasFish = hasFish;
        setPrefSize(80,80);
        setText("??");

    }

    public void handleClick() {
        // TODO: update text
        if(hasFish) {
            // TODO "<><"
            this.setText("<><");
        } else {
            // TODO "X"
            this.setText("X");
        }
        isGuessed = true;
        setDisabled(true);
    }
}

package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * This class represents a game of Tic Tac Toe where one player is a human and the other is an easy level robot.
 * The robot will make random moves.
 */
public class HumanVsRobotEasy extends HumanVsHuman {
    Random random = new Random();

    public void computerTurn() {
        if (!isPlayer1_turn()) {
            makeRandomMove();
        }
    }

    /**
     * Makes a random move for the robot player.
     */
    public void makeRandomMove() {
        while (true) {
            int index = random.nextInt(9);// generate a random index between 0 and 8
            System.out.println(index);
            if (buttons[index].getText().isEmpty()) {// if the button at the index is empty
                buttons[index].setForeground(Color.BLACK);// set the text color to black
                buttons[index].setText("O");// set the button text to O (for robot player)
                label.setText("X turn");// update the label to show it is the human player's turn
                checkGameStatus("O");// check if the game has been won or drawn by the robot player
                setPlayer1_turn(true);// set the turn to the human player
                break;// exit the loop
            }
        }
    }

    /**
     * The actionPerformed method is called when an action event occurs.
     * In this case, it is called when one of the buttons is clicked.
     * If the reset button was clicked, it will reset the game.
     * If the back button was clicked, it will go back to the previous screen.
     * If one of the tic-tac-toe buttons was clicked, it will place an X on the button (if it is the human player's turn)
     * and check if the game has been won or drawn. If the game has not been won or drawn, it will make a move for the robot player.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            setResetButton();
        }
        if (e.getSource() == backButton) {
            frame.dispose();
            new SelectGame().setFrame();
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (isPlayer1_turn()) {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setText("X");
                        checkGameStatus("X");
                        setPlayer1_turn(false);
                        label.setText("O turn");
                        if (checkGameStatus("X").equals("Game not finished")) {// if the game has not been won or drawn by the human player
                            computerTurn();// make a move for the robot player
                        }
                        break;
                    }
                }
            }
        }

    }
}

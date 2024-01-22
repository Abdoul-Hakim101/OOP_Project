package TicTacToe;

import java.awt.*;

/**
 * This class is a subclass of HumanVsRobotEasy, and it overrides the computerTurn method
 * to implement a more advanced AI for the computer player in the Tic-Tac-Toe game.
 */
public class HumanVsRobotMedium extends HumanVsRobotEasy {
    /**
     * Overrides the computerTurn method of the parent class to implement a more advanced AI for the computer player.
     */
    @Override
    public void computerTurn() {
        // Check if it is the turn of player 1 (X). If not, it's the computer's turn (O).
        if (!isPlayer1_turn()) {
            // Try to find a winning move for the computer.
            int AIMove = makeMove("O");
            // If no winning move is found, try to find a move to block the opponent (X) from winning
            int blockOpponent = makeMove("X");
            // If a winning move is found, execute it and update the game state.
            if (AIMove != -1) {
                buttons[AIMove].setForeground(Color.BLACK);
                buttons[AIMove].setText("O");
                label.setText("X turn");
                checkGameStatus("O");
                System.out.println(AIMove + 1 + "Best move");
                setPlayer1_turn(true);
            } else if (blockOpponent != -1) {  // If a blocking move is found, execute it and update the game state.
                buttons[blockOpponent].setForeground(Color.BLACK);
                buttons[blockOpponent].setText("O");
                label.setText("X turn");
                checkGameStatus("O");
                System.out.println(blockOpponent + 1 + " Blocked opponent");
                setPlayer1_turn(true);
                // If no winning or blocking move is found, execute a random move.
            } else {
                makeRandomMove();
            }
        }
    }

    /**
     * This method tries to find a winning or blocking move for the specified player.
     * If a winning or blocking move is found, it returns the index of the button to be moved.
     * Otherwise, it returns -1.
     */
    public int makeMove(String AI_Player) {
        // Check if AI has two in a row and can win with one further move
        for (int i = 0; i <= 6; i += 3) {
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[1 + i].getText().equals(AI_Player)) &&
                    (buttons[2 + i].getText().isEmpty())) {
                return 2 + i;
            }
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[1 + i].getText().isEmpty()) &&
                    (buttons[2 + i].getText().equals(AI_Player))) {
                return 1 + i;
            }
            if (buttons[i].getText().isEmpty() &&
                    (buttons[1 + i].getText().equals(AI_Player)) &&
                    (buttons[2 + i].getText().equals(AI_Player))) {
                return i;
            }
        }
        // Check if AI has two in a col and can win with one further move
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[3 + i].getText().equals(AI_Player)) &&
                    (buttons[6 + i].getText().isEmpty())) {
                return 6 + i;
            }
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[3 + i].getText().isEmpty()) &&
                    (buttons[6 + i].getText().equals(AI_Player))) {
                return 3 + i;
            }
            if (buttons[i].getText().isEmpty() &&
                    (buttons[3 + i].getText().equals(AI_Player)) &&
                    (buttons[6 + i].getText().equals(AI_Player))) {
                return i;
            }
        }

        // Check if AI has two in a diagonal and can win with one further move
        for (int i = 0; i <= 2; i += 2) {
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[4].getText().equals(AI_Player)) &&
                    (buttons[8 - i].getText().isEmpty())) {
                return 8 - i;
            }
            if (buttons[i].getText().equals(AI_Player) &&
                    (buttons[4].getText().isEmpty()) &&
                    (buttons[8 - i].getText().equals(AI_Player))) {
                return 4;
            }
            if (buttons[i].getText().isEmpty() &&
                    (buttons[4].getText().equals(AI_Player)) &&
                    (buttons[8 - i].getText().equals(AI_Player))) {
                return i;
            }
        }

        return -1;
    }
}
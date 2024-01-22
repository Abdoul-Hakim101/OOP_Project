package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class representing a Tic-Tac-Toe game where two human players play against each other.
 * The game is played on a 3x3 game board and the first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.
 * Implements the ActionListener interface to handle button clicks for the game board buttons and the reset and back buttons.
 */
public class HumanVsHuman implements ActionListener {
    // instance variables for the game board buttons, frame, reset button, back button, and label
    JButton[] buttons = new JButton[9];
    JFrame frame = new JFrame();
    JButton resetButton = new JButton();
    JButton backButton = new JButton();
    JLabel label = new JLabel();

    // variable to keep track of which player's turn it is
    private boolean player1_turn = true;

    /**
     * Sets up the game and its components.
     */
    public void setFrame() {
        // initialize the frame and set its layout
        initialize(frame, "Tic-Tac-Toe");
        frame.setLayout(new BorderLayout());
        // add the game field panel and footer panel to the frame
        frame.add(createGameField());
        frame.add(createFooter(), BorderLayout.SOUTH);
        // make the frame visible
        frame.setVisible(true);
    }

    /**
     * Initializes the frame with the given title and sets its size, location, and background color.
     *
     * @param frame the frame to initialize
     * @param title the title for the frame
     */
    public void initialize(JFrame frame, String title) {
        // set the frame's title, size, location, and background color
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(224, 177, 18));
        frame.setLayout(null);
    }

    /**
     * Creates and returns a panel containing the game field buttons.
     *
     * @return the game field panel
     */
    protected JPanel createGameField() {
        // create a panel and set its layout
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(3, 3, 3, 3));
        // add the game board buttons to the panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(224, 177, 18));
            buttons[i].addActionListener(this);
        }
        return button_panel;
    }

    /**
     * Creates and returns a panel containing the reset and back buttons and the game status label.
     *
     * @return the footer panel
     */
    protected JPanel createFooter() {
        // create a panel and add the label, back button, and reset button to it
        JPanel footer_panel = new JPanel();
        label.setText("Game is not started");
        resetButton.setText("Reset");
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);

        backButton.setText("Back");
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        footer_panel.add(label);
        footer_panel.add(backButton);
        footer_panel.add(resetButton);
        return footer_panel;
    }

    /**
     * Resets the game board and sets it to the starting state.
     */
    protected void setResetButton() {
        // clear all the buttons and set the player turn back to player 1
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            player1_turn = true;
            label.setText("Game is not started");
            dis_EnableButtons(true);
        }
    }

    /**
     * Checks the current game state to see if the game has been won or if it is a draw.
     *
     * @param symbol the symbol of the player whose turn it is
     * @return a string indicating the game's current status
     */
    protected String checkGameStatus(String symbol) {
        // check for horizontal wins
        for (int i = 0; i <= 6; i += 3) {
            if (buttons[i].getText().equals(symbol) &&
                    (buttons[1 + i].getText().equals(symbol)) &&
                    (buttons[2 + i].getText().equals(symbol))) {
                // player with the given symbol has won, update the label and disable the buttons
                label.setText(symbol.concat("wins!").toUpperCase());
                dis_EnableButtons(false);
                return "Game finished";
            }
        }
        // check for vertical wins
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(symbol) &&
                    (buttons[3 + i].getText().equals(symbol)) &&
                    (buttons[6 + i].getText().equals(symbol))) {
                // player with the given symbol has won, update the label and disable the buttons
                label.setText(symbol.concat("wins!").toUpperCase());
                dis_EnableButtons(false);
                return "Game finished";
            }
        }
        // check for diagonal wins
        for (int i = 0; i <= 2; i += 2) {
            if (buttons[i].getText().equals(symbol) &&
                    (buttons[4].getText().equals(symbol)) &&
                    (buttons[8 - i].getText().equals(symbol))) {
                // player with the given symbol has won, update the label and disable the buttons
                label.setText(symbol.concat("wins!").toUpperCase());
                dis_EnableButtons(false);
                return "Game finished";
            }
        }

        // check for a draw
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (!buttons[i].getText().isEmpty()) {
                count++;
            }
            if (count == 9) {
                label.setText("DRAW!");
                return "Game finished";
            }
        }
        return "Game not finished";
    }

    /**
     * Enables or disables all the game board buttons.
     *
     * @param type a boolean indicating whether to enable or disable the buttons
     */
    protected void dis_EnableButtons(boolean type) {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(type);
        }
    }

    /**
     * The actionPerformed method listens for actions on the game board buttons, reset button, and back button.
     * If the reset button is clicked, the setResetButton method is called to reset the game board.
     * If the back button is clicked, the current frame is disposed and a new SelectGame frame is created.
     * If a game board button is clicked, the button's text is set to either "X" or "O" depending on the current player's turn.
     * The player's turn is then switched and the game status label is updated to show the current player's turn.
     * The checkGameStatus method is also called to check if the game has been won or if it is a draw.
     *
     * @param e the ActionEvent that occurred
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
                if (player1_turn) {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(Color.BLACK);
                        buttons[i].setText("X");
                        player1_turn = false;
                        label.setText("O turn");
                        checkGameStatus("X");
                    }
                } else {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(Color.BLACK);
                        buttons[i].setText("O");
                        player1_turn = true;
                        label.setText("X turn");
                        checkGameStatus("O");
                    }
                }
            }
        }
    }

    public boolean isPlayer1_turn() {
        return player1_turn;
    }

    public void setPlayer1_turn(boolean player1_turn) {
        this.player1_turn = player1_turn;
    }
}

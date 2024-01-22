package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SelectRobotLevels extends SelectGame {
    private final JFrame frame = new JFrame();


    // Initializes the frame for the SelectRobotLevels class and sets up the components for the frame
    @Override
    public void setFrame() {
        // Set the default close operation, title, size, location, and background color for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select Game Level");
        frame.setResizable(false);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(224, 177, 18));
        frame.setLayout(null);
        // Add a label, combo box, error message, and button to the frame
        setLabel("Choose a level", 20, 80, 120, 200, 30);
        setComboBox(new String[]{"--Please choose an level--", "Easy", "Medium"}, 15, 80, 150, 250, 50);
        setErrorMsg("", 80, 200, 180, 40);
        setButton("Start", 30, 115, 260, 180, 40);
        frame.add(getLabel());
        frame.add(getComboBox());
        frame.add(getErrorMsg());
        frame.add(getButton());
        // Make the frame visible
        frame.setVisible(true);
    }

    // Handle the action event when the combo box or button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the combo box is changed and no longer equals the default value, clear the error message
        if (!getComboBox().getSelectedItem().equals("--Please choose an level--")) {
            setErrorMsg("", 80, 200, 180, 40);
        }
        // If the button is clicked, check the value of the combo box and open the appropriate game window
        if (e.getSource() == getButton()) {
            // If the combo box equals the default value, set the error message
            if (getComboBox().getSelectedItem().equals("--Please choose an level--")) {
                setErrorMsg("Select a level", 80, 200, 180, 40);
            } // If the combo box equals "Easy", close the current window and open the HumanVsRobotEasy window
            else if (getComboBox().getSelectedItem().equals("Easy")) {
                frame.dispose();
                new HumanVsRobotEasy().setFrame();
            } // If the combo box equals "Medium", close the current window and open the HumanVsRobotMedium window
            else if (getComboBox().getSelectedItem().equals("Medium")) {
                frame.dispose();
                new HumanVsRobotMedium().setFrame();
            }
        }
    }
}

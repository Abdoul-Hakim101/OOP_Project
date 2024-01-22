package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class SelectGame extends UserName {
    private JFrame frame = new JFrame();
    private JComboBox<String> comboBox;

    SelectGame() {

    }

    // Set up the game selection frame
    @Override
    public void setFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select Player");
        frame.setResizable(false);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(224, 177, 18));
        frame.setLayout(null);
        setLabel("Choose a game:", 20, 80, 120, 200, 30);
        setButton("Next", 30, 115, 260, 180, 40);
        setErrorMsg("", 85, 200, 180, 40);
        setComboBox(new String[]{"--Please choose an option--", "Play against a friend", "Play against a robot"}, 15, 80, 150, 250, 50);
        frame.add(getLabel());
        frame.add(getComboBox());
        frame.add(getErrorMsg());
        frame.add(getButton());
        frame.setVisible(true);
    }

    // Set up the game selection combo box
    public void setComboBox(String[] list, int fontSize, int x, int y, int width, int height) {
        comboBox = new JComboBox<>(list);
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Ink", Font.BOLD, fontSize));
        comboBox.addActionListener(this);
    }

    // Get the game selection combo box
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    // Handle button clicks and game selection
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == getButton()) {
            // Validate the game selection
            if (comboBox.getSelectedItem().equals("--Please choose an option--")) {
                setErrorMsg("Please select a game", 85, 200, 180, 40);
            } else if (comboBox.getSelectedItem().equals("Play against a friend")) {
                // Write the game selection to a file and start a human vs. human game
                try {
                    FileWriter writer = new FileWriter(file, true);
                    LocalTime now = LocalTime.now();
                    writer.write("[ Started playing against a friend at :" + now + " ]\n");
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getCause());
                }
                frame.dispose();
                new HumanVsHuman().setFrame();
            } else if (comboBox.getSelectedItem().equals("Play against a robot")) {
                // Write the game selection to a file and start a human vs. robot game
                try {
                    FileWriter writer = new FileWriter(file, true);
                    LocalTime now = LocalTime.now();
                    writer.write("[ Started playing against a robot at :" + now + " ]\n");
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getCause());
                }
                frame.dispose();
                new SelectRobotLevels().setFrame();
            }
        }
    }
}
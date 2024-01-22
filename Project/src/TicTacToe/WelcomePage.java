package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePage implements ActionListener {
    private JFrame frame = new JFrame();
    private JButton button = new JButton();
    private JLabel label = new JLabel();

    // Sets up and displays the welcome page
    public void setFrame() {
        // Initialize the frame and add a title
        initialize(frame, "Tic Tac Toe");
        // Add three text labels to the frame
        frame.add(setTextField("Welcome to", 50));
        frame.add(setTextField("Tic-Tac-Toe", 120));
        frame.add(setTextField("Game", 180));
        // Add a button to the frame
        setButton("Start", 40, 125, 280, 200, 50);
        frame.add(getButton());
        // Set the frame to be visible
        frame.setVisible(true);
    }

    // Initializes the frame with the specified title and layout
    public void initialize(JFrame frame, String title) {
        // Set the default close operation and title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        // Set the frame to be non-resizable
        frame.setResizable(false);
        // Set the size and location of the frame
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        // Set the background color of the frame
        frame.getContentPane().setBackground(new Color(224, 177, 18));
        // Set the layout of the frame to be null
        frame.setLayout(null);
    }


    private JLabel setTextField(String text, int index) {
        JLabel label = new JLabel();
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Ink Free", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText(text);
        label.setBounds(0, index, 450, 100);
        return label;
    }

    // Creates and returns a JLabel object with the specified text and font
    public void setLabel(String text, int fontSize, int x, int y, int width, int height) {
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Ink", Font.BOLD, fontSize));
        label.setText(text);
        label.setBounds(x, y, width, height);
    }

    // Sets the title, font, and size of the button object
    public void setButton(String title, int fontSize, int x, int y, int width, int height) {
        button.setText(title);
        button.setFont(new Font("Ink", Font.BOLD, fontSize));
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.addActionListener(this);
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the event is the button object
        if (e.getSource() == button) {
            // Dispose of the current frame
            frame.dispose();
            // Create a new UserName object and display its user interface
            new UserName().setFrame();
        }
    }
}
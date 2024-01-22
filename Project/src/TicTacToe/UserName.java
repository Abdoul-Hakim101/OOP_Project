package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserName extends WelcomePage {
    private JLabel errorMsg = new JLabel();
    JTextField name = new JTextField();
    private JFrame frame = new JFrame();
    File file = new File("file.txt");

    // Set up the username input frame
    @Override
    public void setFrame() {
        initialize(frame, "Username");
        setLabel("Username", 30, 20, 100, 150, 30);
        setErrorMsg("", 190, 135, 180, 40);
        setButton("Next", 30, 125, 220, 180, 40);
        frame.add(getLabel());
        frame.add(getName());
        frame.add(getErrorMsg());
        frame.add(getButton());
        frame.setVisible(true);
    }

    // Sets the text and position of the error message label
    public void setErrorMsg(String text, int x, int y, int width, int height) {
        errorMsg.setText(text);
        errorMsg.setForeground(Color.red);
        errorMsg.setBounds(x, y, width, height);
    }

    // Set up the username text field and add an action listener
    private JTextField getName() {
        name.setBounds(190, 100, 180, 40);
        name.addActionListener(this);
        return name;
    }
    // Get the error message label
    public JLabel getErrorMsg() {
        return errorMsg;
    }
   // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getButton()) {
            // Validate the username
            if (name.getText().trim().isBlank()) {
                setErrorMsg("Please enter a username", 190, 135, 180, 40);
            } else if (name.getText().trim().length() < 4) {
                setErrorMsg("A username must be at least four characters long.", 80, 135, 300, 40);
            } else {
                // Write the username to a file and move on to the next page
                try {
                    LocalDateTime dateTime = LocalDateTime.now();
                    FileWriter writer = new FileWriter(file, true);
                    writer.write("{ The username " + name.getText() + " was recorded as logging in at the time of: " + dateTime + " }\n");
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getCause());
                }
                System.out.println("Username: " + name.getText());
                frame.dispose();
                new SelectGame().setFrame();
            }
        }
    }
}
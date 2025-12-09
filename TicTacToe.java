import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    
    JButton[] buttons = new JButton[9];
    boolean playerX = true; // X starts
    
    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        
        // Create 9 buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        
        add(panel, BorderLayout.CENTER);
        
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        
        if (!clicked.getText().equals("")) {
            return; // If already clicked, ignore
        }
        
        clicked.setText(playerX ? "X" : "O");
        
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, 
                "Player " + (playerX ? "X" : "O") + " Wins!");
            disableButtons();
        } else if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
        } else {
            playerX = !playerX; // Switch turn
        }
    }
    
    // Check all 8 win conditions
    public boolean checkWin() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
            {0, 4, 8}, {2, 4, 6}               // diagonals
        };
        
        for (int[] condition : winConditions) {
            if (buttons[condition[0]].getText().equals(buttons[condition[1]].getText()) &&
                buttons[condition[1]].getText().equals(buttons[condition[2]].getText()) &&
                !buttons[condition[0]].getText().equals("")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    
    public void disableButtons() {
        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }
    }
    
    public void resetGame() {
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setEnabled(true);
        }
        playerX = true;
    }
    
    public static void main(String[] args) {
        new TicTacToe();
    }
}

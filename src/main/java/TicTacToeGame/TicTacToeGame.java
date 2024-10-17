package TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeGame extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    JButton[][] buttons;
    private char currentPlayer;
    private boolean isGameWon;
    private JLabel statusLabel;
    public Random random;

    public TicTacToeGame() {
        buttons = new JButton[SIZE][SIZE];
        currentPlayer = 'X';
        isGameWon = false;
        random = new Random();
        initializeUI();
    }
    private void computerMove() {
        if (!isGameWon && currentPlayer == 'O') {
            int row, col;
            do {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            } while (!buttons[row][col].getText().isEmpty());

            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setEnabled(false); // Disable the button

            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                isGameWon = true;
            } else if (isBoardFull()) {
                statusLabel.setText("It's a draw!");
                isGameWon = true;
            } else {
                currentPlayer = 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }
    private void initializeUI() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 350);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn", SwingConstants.CENTER);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].addActionListener(this);
                boardPanel.add(buttons[row][col]);
            }
        }

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());

        JPanel controlPanel = new JPanel();
        controlPanel.add(restartButton);

        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameWon) {
            return; // If the game is already won, ignore further moves
        }

        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().isEmpty()) {
            return; // If the button is already marked, ignore the click
        }

        if (currentPlayer == 'X') {
            // Player's turn
            clickedButton.setText("X");
            clickedButton.setEnabled(false);

            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                isGameWon = true;
            } else if (isBoardFull()) {
                statusLabel.setText("It's a draw!");
                isGameWon = true;
            } else {
                currentPlayer = 'O';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
                // Now it's the player's turn; no need to call computerMove
            }
        }

        if (currentPlayer == 'O') {
            computerMove();
        }
    }

    boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)) ||
                    buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        return buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ||
                buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer));
    }

    boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    void restartGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
        isGameWon = false;
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGame::new);
    }
}

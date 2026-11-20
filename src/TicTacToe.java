import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToe() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel("Ход игрока X", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        boardPanel.setBackground(new Color(240, 240, 240));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Segoe UI", Font.BOLD, 72));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> handleClick(row, col, statusLabel));
                boardPanel.add(buttons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Новая игра");
        resetButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetGame(statusLabel));
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleClick(int row, int col, JLabel statusLabel) {
        if (gameOver || !buttons[row][col].getText().isEmpty()) {
            return;
        }

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setForeground(currentPlayer == 'X' ? new Color(30, 100, 220) : new Color(220, 50, 50));

        if (checkWin()) {
            statusLabel.setText("Победил игрок " + currentPlayer + "!");
            statusLabel.setForeground(new Color(0, 150, 0));
            gameOver = true;
            return;
        }

        if (isBoardFull()) {
            statusLabel.setText("Ничья!");
            statusLabel.setForeground(Color.ORANGE.darker());
            gameOver = true;
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Ход игрока " + currentPlayer);
        statusLabel.setForeground(Color.BLACK);
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty()
                    && buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().isEmpty()
                    && buttons[0][j].getText().equals(buttons[1][j].getText())
                    && buttons[1][j].getText().equals(buttons[2][j].getText())) {
                return true;
            }
        }
        if (!buttons[0][0].getText().isEmpty()
                && buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().isEmpty()
                && buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame(JLabel statusLabel) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK);
            }
        }
        currentPlayer = 'X';
        gameOver = false;
        statusLabel.setText("Ход игрока X");
        statusLabel.setForeground(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}

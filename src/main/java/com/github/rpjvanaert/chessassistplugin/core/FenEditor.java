package com.github.rpjvanaert.chessassistplugin.core;

import javax.swing.*;
import java.awt.*;

public class FenEditor {

    /**
     * Detects a FEN string and triggers a pop-up with a chessboard visualization.
     * @param fen the FEN string to visualize
     */
    public void editFen(String fen) {
        if (isValidFen(fen)) {
            showChessBoard(fen);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid FEN string!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Validates the FEN string.
     * @param fen the FEN string
     * @return true if valid, false otherwise
     */
    private boolean isValidFen(String fen) {
        // Basic validation for FEN format (can be expanded)
        return fen != null && fen.split(" ").length == 6;
    }

    /**
     * Displays a pop-up with a chessboard visualization.
     * @param fen the FEN string to visualize
     */
    private void showChessBoard(String fen) {
        JFrame frame = new JFrame("FEN Editor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        String[] rows = fen.split(" ")[0].split("/");

        for (String row : rows) {
            for (char c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    int emptySquares = Character.getNumericValue(c);
                    for (int i = 0; i < emptySquares; i++) {
                        boardPanel.add(createSquare(Color.LIGHT_GRAY));
                    }
                } else {
                    boardPanel.add(createPieceSquare(c));
                }
            }
        }

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    /**
     * Creates an empty square for the chessboard.
     * @param color the color of the square
     * @return the JPanel representing the square
     */
    private JPanel createSquare(Color color) {
        JPanel square = new JPanel();
        square.setBackground(color);
        return square;
    }

    /**
     * Creates a square with a chess piece.
     * @param piece the piece character
     * @return the JPanel representing the square
     */
    private JPanel createPieceSquare(char piece) {
        JPanel square = createSquare(Color.WHITE);
        JLabel label = new JLabel(String.valueOf(piece), SwingConstants.CENTER);
        square.add(label);
        return square;
    }
}

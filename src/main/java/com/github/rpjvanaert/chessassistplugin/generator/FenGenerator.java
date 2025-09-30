package com.github.rpjvanaert.chessassistplugin.generator;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;

/**
 * Interface for generating FEN positions.
 */
public interface FenGenerator {
    
    /**
     * Generates a FEN position for the standard starting position.
     * 
     * @return FEN position for the initial chess setup
     */
    FenPosition generateStartingPosition();
    
    /**
     * Generates an empty board FEN position.
     * 
     * @return FEN position with no pieces
     */
    FenPosition generateEmptyBoard();
    
    /**
     * Generates a custom FEN position from components.
     * 
     * @param piecePlacement piece placement string
     * @param activeColor active color (w or b)
     * @param castling castling availability
     * @param enPassant en passant target square
     * @param halfmove halfmove clock
     * @param fullmove fullmove number
     * @return generated FEN position
     */
    FenPosition generateCustomPosition(String piecePlacement, String activeColor,
                                      String castling, String enPassant,
                                      int halfmove, int fullmove);
}

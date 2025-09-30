package com.github.rpjvanaert.chessassistplugin.core;

/**
 * Represents a chess position in FEN (Forsyth-Edwards Notation) format.
 * 
 * FEN format consists of six fields separated by spaces:
 * 1. Piece placement (from white's perspective, rank 8 to rank 1)
 * 2. Active color (w or b)
 * 3. Castling availability (KQkq or -)
 * 4. En passant target square (e.g., e3 or -)
 * 5. Halfmove clock (number of halfmoves since last capture or pawn move)
 * 6. Fullmove number (increments after Black's move)
 */
public interface FenPosition {
    
    /**
     * Returns the complete FEN string representation.
     * @return the FEN string
     */
    String toFenString();
    
    /**
     * Returns the piece placement part of the FEN.
     * @return piece placement string
     */
    String getPiecePlacement();
    
    /**
     * Returns the active color (w or b).
     * @return active color
     */
    String getActiveColor();
    
    /**
     * Returns the castling availability string.
     * @return castling availability
     */
    String getCastlingAvailability();
    
    /**
     * Returns the en passant target square.
     * @return en passant target square or "-"
     */
    String getEnPassantTarget();
    
    /**
     * Returns the halfmove clock value.
     * @return halfmove clock
     */
    int getHalfmoveClock();
    
    /**
     * Returns the fullmove number.
     * @return fullmove number
     */
    int getFullmoveNumber();
}

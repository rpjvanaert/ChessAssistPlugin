package com.github.rpjvanaert.chessassistplugin.editor;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;

/**
 * Interface for editing FEN positions.
 */
public interface FenEditor {
    
    /**
     * Sets the piece placement part of the FEN.
     * 
     * @param position the FEN position to modify
     * @param piecePlacement new piece placement
     * @return new FEN position with updated piece placement
     */
    FenPosition setPiecePlacement(FenPosition position, String piecePlacement);
    
    /**
     * Sets the active color.
     * 
     * @param position the FEN position to modify
     * @param activeColor new active color (w or b)
     * @return new FEN position with updated active color
     */
    FenPosition setActiveColor(FenPosition position, String activeColor);
    
    /**
     * Sets the castling availability.
     * 
     * @param position the FEN position to modify
     * @param castling new castling availability
     * @return new FEN position with updated castling
     */
    FenPosition setCastlingAvailability(FenPosition position, String castling);
    
    /**
     * Sets the en passant target square.
     * 
     * @param position the FEN position to modify
     * @param enPassant new en passant target
     * @return new FEN position with updated en passant
     */
    FenPosition setEnPassantTarget(FenPosition position, String enPassant);
    
    /**
     * Sets the halfmove clock.
     * 
     * @param position the FEN position to modify
     * @param halfmove new halfmove clock value
     * @return new FEN position with updated halfmove clock
     */
    FenPosition setHalfmoveClock(FenPosition position, int halfmove);
    
    /**
     * Sets the fullmove number.
     * 
     * @param position the FEN position to modify
     * @param fullmove new fullmove number
     * @return new FEN position with updated fullmove number
     */
    FenPosition setFullmoveNumber(FenPosition position, int fullmove);
}

package com.github.rpjvanaert.chessassistplugin.editor;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;
import com.github.rpjvanaert.chessassistplugin.core.FenPositionImpl;

/**
 * Default implementation of FenEditor.
 * Creates new immutable FenPosition objects with modifications.
 */
public class FenEditorImpl implements FenEditor {
    
    @Override
    public FenPosition setPiecePlacement(FenPosition position, String piecePlacement) {
        return new FenPositionImpl(
            piecePlacement,
            position.getActiveColor(),
            position.getCastlingAvailability(),
            position.getEnPassantTarget(),
            position.getHalfmoveClock(),
            position.getFullmoveNumber()
        );
    }
    
    @Override
    public FenPosition setActiveColor(FenPosition position, String activeColor) {
        return new FenPositionImpl(
            position.getPiecePlacement(),
            activeColor,
            position.getCastlingAvailability(),
            position.getEnPassantTarget(),
            position.getHalfmoveClock(),
            position.getFullmoveNumber()
        );
    }
    
    @Override
    public FenPosition setCastlingAvailability(FenPosition position, String castling) {
        return new FenPositionImpl(
            position.getPiecePlacement(),
            position.getActiveColor(),
            castling,
            position.getEnPassantTarget(),
            position.getHalfmoveClock(),
            position.getFullmoveNumber()
        );
    }
    
    @Override
    public FenPosition setEnPassantTarget(FenPosition position, String enPassant) {
        return new FenPositionImpl(
            position.getPiecePlacement(),
            position.getActiveColor(),
            position.getCastlingAvailability(),
            enPassant,
            position.getHalfmoveClock(),
            position.getFullmoveNumber()
        );
    }
    
    @Override
    public FenPosition setHalfmoveClock(FenPosition position, int halfmove) {
        return new FenPositionImpl(
            position.getPiecePlacement(),
            position.getActiveColor(),
            position.getCastlingAvailability(),
            position.getEnPassantTarget(),
            halfmove,
            position.getFullmoveNumber()
        );
    }
    
    @Override
    public FenPosition setFullmoveNumber(FenPosition position, int fullmove) {
        return new FenPositionImpl(
            position.getPiecePlacement(),
            position.getActiveColor(),
            position.getCastlingAvailability(),
            position.getEnPassantTarget(),
            position.getHalfmoveClock(),
            fullmove
        );
    }
}

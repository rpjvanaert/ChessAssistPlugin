package com.github.rpjvanaert.chessassistplugin.core;

import java.util.Objects;

/**
 * Default implementation of FenPosition.
 */
public class FenPositionImpl implements FenPosition {
    private final String piecePlacement;
    private final String activeColor;
    private final String castlingAvailability;
    private final String enPassantTarget;
    private final int halfmoveClock;
    private final int fullmoveNumber;
    
    public FenPositionImpl(String piecePlacement, String activeColor, 
                          String castlingAvailability, String enPassantTarget,
                          int halfmoveClock, int fullmoveNumber) {
        this.piecePlacement = piecePlacement;
        this.activeColor = activeColor;
        this.castlingAvailability = castlingAvailability;
        this.enPassantTarget = enPassantTarget;
        this.halfmoveClock = halfmoveClock;
        this.fullmoveNumber = fullmoveNumber;
    }
    
    @Override
    public String toFenString() {
        return String.format("%s %s %s %s %d %d",
                piecePlacement, activeColor, castlingAvailability,
                enPassantTarget, halfmoveClock, fullmoveNumber);
    }
    
    @Override
    public String getPiecePlacement() {
        return piecePlacement;
    }
    
    @Override
    public String getActiveColor() {
        return activeColor;
    }
    
    @Override
    public String getCastlingAvailability() {
        return castlingAvailability;
    }
    
    @Override
    public String getEnPassantTarget() {
        return enPassantTarget;
    }
    
    @Override
    public int getHalfmoveClock() {
        return halfmoveClock;
    }
    
    @Override
    public int getFullmoveNumber() {
        return fullmoveNumber;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FenPositionImpl that = (FenPositionImpl) o;
        return halfmoveClock == that.halfmoveClock &&
               fullmoveNumber == that.fullmoveNumber &&
               Objects.equals(piecePlacement, that.piecePlacement) &&
               Objects.equals(activeColor, that.activeColor) &&
               Objects.equals(castlingAvailability, that.castlingAvailability) &&
               Objects.equals(enPassantTarget, that.enPassantTarget);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(piecePlacement, activeColor, castlingAvailability,
                          enPassantTarget, halfmoveClock, fullmoveNumber);
    }
    
    @Override
    public String toString() {
        return toFenString();
    }
}

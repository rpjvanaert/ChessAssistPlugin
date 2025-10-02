package com.github.rpjvanaert.chessassistplugin.viewer;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;

/**
 * Default implementation of FenViewer that creates ASCII board representations.
 */
public class FenViewerImpl implements FenViewer {
    
    @Override
    public String toAsciiBoard(FenPosition position) {
        return toAsciiBoard(position, true);
    }
    
    @Override
    public String toAsciiBoard(FenPosition position, boolean showCoordinates) {
        StringBuilder sb = new StringBuilder();
        String[] ranks = position.getPiecePlacement().split("/");
        
        if (showCoordinates) {
            sb.append("\n  +---+---+---+---+---+---+---+---+\n");
        } else {
            sb.append("+---+---+---+---+---+---+---+---+\n");
        }
        
        for (int i = 0; i < ranks.length; i++) {
            int rankNumber = 8 - i;
            if (showCoordinates) {
                sb.append(rankNumber).append(" |");
            } else {
                sb.append("|");
            }
            
            String rank = ranks[i];
            for (char c : rank.toCharArray()) {
                if (Character.isDigit(c)) {
                    int emptySquares = Character.getNumericValue(c);
                    for (int j = 0; j < emptySquares; j++) {
                        sb.append("   |");
                    }
                } else {
                    sb.append(" ").append(c).append(" |");
                }
            }
            sb.append("\n");
            
            if (showCoordinates) {
                sb.append("  +---+---+---+---+---+---+---+---+\n");
            } else {
                sb.append("+---+---+---+---+---+---+---+---+\n");
            }
        }
        
        if (showCoordinates) {
            sb.append("    a   b   c   d   e   f   g   h\n");
        }
        
        return sb.toString();
    }
    
    @Override
    public String toDetailedString(FenPosition position) {
        StringBuilder sb = new StringBuilder();
        sb.append("FEN: ").append(position.toFenString()).append("\n\n");
        sb.append("Position Details:\n");
        sb.append("  Piece Placement: ").append(position.getPiecePlacement()).append("\n");
        sb.append("  Active Color: ").append(position.getActiveColor().equals("w") ? "White" : "Black").append("\n");
        sb.append("  Castling: ").append(position.getCastlingAvailability()).append("\n");
        sb.append("  En Passant: ").append(position.getEnPassantTarget()).append("\n");
        sb.append("  Halfmove Clock: ").append(position.getHalfmoveClock()).append("\n");
        sb.append("  Fullmove Number: ").append(position.getFullmoveNumber()).append("\n");
        sb.append("\n");
        sb.append(toAsciiBoard(position, true));
        
        return sb.toString();
    }
}

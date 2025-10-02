package com.github.rpjvanaert.chessassistplugin.core;

/**
 * Interface for parsing FEN strings into FenPosition objects.
 */
public interface FenParser {
    
    /**
     * Parses a FEN string into a FenPosition object.
     * 
     * @param fen the FEN string to parse
     * @return the parsed FenPosition
     * @throws IllegalArgumentException if the FEN string is invalid
     */
    FenPosition parse(String fen) throws IllegalArgumentException;
    
    /**
     * Attempts to parse a FEN string, returning null if invalid.
     * 
     * @param fen the FEN string to parse
     * @return the parsed FenPosition or null if invalid
     */
    default FenPosition tryParse(String fen) {
        try {
            return parse(fen);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

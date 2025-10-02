package com.github.rpjvanaert.chessassistplugin.core;

/**
 * Interface for validating FEN strings.
 */
public interface FenValidator {
    
    /**
     * Validates a FEN string.
     * 
     * @param fen the FEN string to validate
     * @return validation result
     */
    FenValidationResult validate(String fen);
    
    /**
     * Checks if a FEN string is valid.
     * 
     * @param fen the FEN string to check
     * @return true if valid, false otherwise
     */
    default boolean isValid(String fen) {
        return validate(fen).isValid();
    }
}

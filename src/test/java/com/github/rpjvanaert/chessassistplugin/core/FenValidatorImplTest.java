package com.github.rpjvanaert.chessassistplugin.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FenValidatorImplTest {
    
    private final FenValidator validator = new FenValidatorImpl();
    
    @Test
    void testValidStartingPosition() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenValidationResult result = validator.validate(fen);
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }
    
    @Test
    void testValidPosition() {
        String fen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        assertTrue(validator.isValid(fen));
    }
    
    @Test
    void testInvalidNullFen() {
        FenValidationResult result = validator.validate(null);
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
    }
    
    @Test
    void testInvalidEmptyFen() {
        FenValidationResult result = validator.validate("");
        assertFalse(result.isValid());
    }
    
    @Test
    void testInvalidPartCount() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains("6 parts"));
    }
    
    @Test
    void testInvalidActiveColor() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR x KQkq - 0 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains("active color"));
    }
    
    @Test
    void testInvalidCastling() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KKK - 0 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains("castling"));
    }
    
    @Test
    void testInvalidEnPassant() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq e5 0 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains("en passant"));
    }
    
    @Test
    void testValidEnPassant() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq e3 0 1";
        assertTrue(validator.isValid(fen));
    }
    
    @Test
    void testInvalidHalfmoveClock() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - -1 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
    }
    
    @Test
    void testInvalidFullmoveNumber() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains("Fullmove"));
    }
    
    @Test
    void testInvalidPiecePlacementTooManyRanks() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
    }
    
    @Test
    void testInvalidPiecePlacementWrongSquareCount() {
        String fen = "rnbqkbnr/pppppppp/7/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenValidationResult result = validator.validate(fen);
        assertFalse(result.isValid());
    }
}

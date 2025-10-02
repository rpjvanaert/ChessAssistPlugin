package com.github.rpjvanaert.chessassistplugin.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FenParserImplTest {
    
    private final FenParser parser = new FenParserImpl();
    
    @Test
    void testParseStartingPosition() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenPosition position = parser.parse(fen);
        
        assertNotNull(position);
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", position.getPiecePlacement());
        assertEquals("w", position.getActiveColor());
        assertEquals("KQkq", position.getCastlingAvailability());
        assertEquals("-", position.getEnPassantTarget());
        assertEquals(0, position.getHalfmoveClock());
        assertEquals(1, position.getFullmoveNumber());
        assertEquals(fen, position.toFenString());
    }
    
    @Test
    void testParseCustomPosition() {
        String fen = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        FenPosition position = parser.parse(fen);
        
        assertNotNull(position);
        assertEquals("b", position.getActiveColor());
        assertEquals(1, position.getHalfmoveClock());
        assertEquals(2, position.getFullmoveNumber());
    }
    
    @Test
    void testParseInvalidFenThrowsException() {
        String invalidFen = "invalid fen string";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(invalidFen));
    }
    
    @Test
    void testTryParseValidFen() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenPosition position = parser.tryParse(fen);
        assertNotNull(position);
    }
    
    @Test
    void testTryParseInvalidFenReturnsNull() {
        String invalidFen = "invalid fen string";
        FenPosition position = parser.tryParse(invalidFen);
        assertNull(position);
    }
    
    @Test
    void testParseWithEnPassant() {
        String fen = "rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2";
        FenPosition position = parser.parse(fen);
        assertEquals("d6", position.getEnPassantTarget());
    }
}

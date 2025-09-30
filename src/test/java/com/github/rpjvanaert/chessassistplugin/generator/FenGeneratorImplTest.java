package com.github.rpjvanaert.chessassistplugin.generator;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FenGeneratorImplTest {
    
    private FenGenerator generator;
    
    @BeforeEach
    void setUp() {
        generator = new FenGeneratorImpl();
    }
    
    @Test
    void testGenerateStartingPosition() {
        FenPosition position = generator.generateStartingPosition();
        
        assertNotNull(position);
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", position.getPiecePlacement());
        assertEquals("w", position.getActiveColor());
        assertEquals("KQkq", position.getCastlingAvailability());
        assertEquals("-", position.getEnPassantTarget());
        assertEquals(0, position.getHalfmoveClock());
        assertEquals(1, position.getFullmoveNumber());
    }
    
    @Test
    void testGenerateEmptyBoard() {
        FenPosition position = generator.generateEmptyBoard();
        
        assertNotNull(position);
        assertEquals("8/8/8/8/8/8/8/8", position.getPiecePlacement());
        assertEquals("w", position.getActiveColor());
        assertEquals("-", position.getCastlingAvailability());
        assertEquals("-", position.getEnPassantTarget());
        assertEquals(0, position.getHalfmoveClock());
        assertEquals(1, position.getFullmoveNumber());
    }
    
    @Test
    void testGenerateCustomPosition() {
        FenPosition position = generator.generateCustomPosition(
            "4k3/8/8/8/8/8/8/4K3",
            "b",
            "-",
            "e6",
            5,
            10
        );
        
        assertNotNull(position);
        assertEquals("4k3/8/8/8/8/8/8/4K3", position.getPiecePlacement());
        assertEquals("b", position.getActiveColor());
        assertEquals("-", position.getCastlingAvailability());
        assertEquals("e6", position.getEnPassantTarget());
        assertEquals(5, position.getHalfmoveClock());
        assertEquals(10, position.getFullmoveNumber());
    }
    
    @Test
    void testStartingPositionFenString() {
        FenPosition position = generator.generateStartingPosition();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 
                    position.toFenString());
    }
}

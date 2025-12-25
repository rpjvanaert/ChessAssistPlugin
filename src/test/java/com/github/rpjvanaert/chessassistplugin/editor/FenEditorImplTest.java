package com.github.rpjvanaert.chessassistplugin.editor;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;
import com.github.rpjvanaert.chessassistplugin.core.FenPositionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FenEditorImplTest {
    
    private FenEditor editor;
    private FenPosition startingPosition;
    
    @BeforeEach
    void setUp() {
        editor = new FenEditorImpl();
        startingPosition = new FenPositionImpl(
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            "w", "KQkq", "-", 0, 1
        );
    }
    
    @Test
    void testSetPiecePlacement() {
        String newPlacement = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R";
        FenPosition modified = editor.setPiecePlacement(startingPosition, newPlacement);
        
        assertEquals(newPlacement, modified.getPiecePlacement());
        // Other fields should remain unchanged
        assertEquals("w", modified.getActiveColor());
        assertEquals("KQkq", modified.getCastlingAvailability());
    }
    
    @Test
    void testSetActiveColor() {
        FenPosition modified = editor.setActiveColor(startingPosition, "b");
        
        assertEquals("b", modified.getActiveColor());
        // Original should be unchanged
        assertEquals("w", startingPosition.getActiveColor());
    }
    
    @Test
    void testSetCastlingAvailability() {
        FenPosition modified = editor.setCastlingAvailability(startingPosition, "Kq");
        
        assertEquals("Kq", modified.getCastlingAvailability());
        assertEquals("KQkq", startingPosition.getCastlingAvailability());
    }
    
    @Test
    void testSetEnPassantTarget() {
        FenPosition modified = editor.setEnPassantTarget(startingPosition, "e3");
        
        assertEquals("e3", modified.getEnPassantTarget());
        assertEquals("-", startingPosition.getEnPassantTarget());
    }
    
    @Test
    void testSetHalfmoveClock() {
        FenPosition modified = editor.setHalfmoveClock(startingPosition, 5);
        
        assertEquals(5, modified.getHalfmoveClock());
        assertEquals(0, startingPosition.getHalfmoveClock());
    }
    
    @Test
    void testSetFullmoveNumber() {
        FenPosition modified = editor.setFullmoveNumber(startingPosition, 10);
        
        assertEquals(10, modified.getFullmoveNumber());
        assertEquals(1, startingPosition.getFullmoveNumber());
    }
    
    @Test
    void testChainedEdits() {
        FenPosition modified = editor.setActiveColor(startingPosition, "b");
        modified = editor.setHalfmoveClock(modified, 1);
        modified = editor.setFullmoveNumber(modified, 2);
        
        assertEquals("b", modified.getActiveColor());
        assertEquals(1, modified.getHalfmoveClock());
        assertEquals(2, modified.getFullmoveNumber());
        
        // Original should be unchanged
        assertEquals("w", startingPosition.getActiveColor());
        assertEquals(0, startingPosition.getHalfmoveClock());
        assertEquals(1, startingPosition.getFullmoveNumber());
    }
}

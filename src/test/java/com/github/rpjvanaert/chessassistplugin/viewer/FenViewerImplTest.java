package com.github.rpjvanaert.chessassistplugin.viewer;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;
import com.github.rpjvanaert.chessassistplugin.core.FenPositionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FenViewerImplTest {
    
    private FenViewer viewer;
    private FenPosition startingPosition;
    
    @BeforeEach
    void setUp() {
        viewer = new FenViewerImpl();
        startingPosition = new FenPositionImpl(
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            "w", "KQkq", "-", 0, 1
        );
    }
    
    @Test
    void testToAsciiBoard() {
        String board = viewer.toAsciiBoard(startingPosition);
        
        assertNotNull(board);
        assertTrue(board.contains("r"));
        assertTrue(board.contains("R"));
        assertTrue(board.contains("K"));
        assertTrue(board.contains("k"));
    }
    
    @Test
    void testToAsciiBoardWithCoordinates() {
        String board = viewer.toAsciiBoard(startingPosition, true);
        
        assertTrue(board.contains("8"));
        assertTrue(board.contains("1"));
        assertTrue(board.contains("a"));
        assertTrue(board.contains("h"));
    }
    
    @Test
    void testToAsciiBoardWithoutCoordinates() {
        String board = viewer.toAsciiBoard(startingPosition, false);
        
        assertFalse(board.contains("a   b   c"));
        assertFalse(board.contains("8 |"));
    }
    
    @Test
    void testToDetailedString() {
        String details = viewer.toDetailedString(startingPosition);
        
        assertTrue(details.contains("FEN:"));
        assertTrue(details.contains("Piece Placement:"));
        assertTrue(details.contains("Active Color:"));
        assertTrue(details.contains("Castling:"));
        assertTrue(details.contains("En Passant:"));
        assertTrue(details.contains("Halfmove Clock:"));
        assertTrue(details.contains("Fullmove Number:"));
        assertTrue(details.contains("White"));
    }
    
    @Test
    void testEmptyBoard() {
        FenPosition emptyBoard = new FenPositionImpl(
            "8/8/8/8/8/8/8/8", "w", "-", "-", 0, 1
        );
        String board = viewer.toAsciiBoard(emptyBoard);
        
        assertNotNull(board);
        assertTrue(board.contains("|   |   |   |   |   |   |   |   |"));
    }
    
    @Test
    void testCustomPosition() {
        FenPosition customPosition = new FenPositionImpl(
            "4k3/8/8/8/8/8/8/4K3", "w", "-", "-", 0, 1
        );
        String board = viewer.toAsciiBoard(customPosition);
        
        assertTrue(board.contains("k"));
        assertTrue(board.contains("K"));
    }
}

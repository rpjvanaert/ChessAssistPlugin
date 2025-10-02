package com.github.rpjvanaert.chessassistplugin.detector;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FenDetectorImplTest {
    
    private final FenDetector detector = new FenDetectorImpl();
    
    @Test
    void testDetectSingleFen() {
        String text = "Starting position: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        List<FenDetection> detections = detector.detectAll(text);
        
        assertEquals(1, detections.size());
        FenDetection detection = detections.get(0);
        assertTrue(detection.isValid());
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", detection.getFenString());
    }
    
    @Test
    void testDetectMultipleFens() {
        String text = "Position 1: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 " +
                     "Position 2: rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        List<FenDetection> detections = detector.detectAll(text);
        
        assertEquals(2, detections.size());
        assertTrue(detections.get(0).isValid());
        assertTrue(detections.get(1).isValid());
    }
    
    @Test
    void testDetectNoFen() {
        String text = "This text contains no FEN strings";
        List<FenDetection> detections = detector.detectAll(text);
        
        assertTrue(detections.isEmpty());
    }
    
    @Test
    void testDetectFirstFen() {
        String text = "First: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 " +
                     "Second: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenDetection detection = detector.detectFirst(text);
        
        assertNotNull(detection);
        assertTrue(detection.getStartIndex() < 50); // First FEN should be early in text
    }
    
    @Test
    void testDetectFirstReturnsNullWhenNoneFound() {
        String text = "No FEN here";
        FenDetection detection = detector.detectFirst(text);
        assertNull(detection);
    }
    
    @Test
    void testContainsFen() {
        String textWithFen = "FEN: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String textWithoutFen = "No FEN here";
        
        assertTrue(detector.containsFen(textWithFen));
        assertFalse(detector.containsFen(textWithoutFen));
    }
    
    @Test
    void testDetectionIndices() {
        String text = "Start rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 End";
        List<FenDetection> detections = detector.detectAll(text);
        
        assertEquals(1, detections.size());
        FenDetection detection = detections.get(0);
        assertTrue(detection.getStartIndex() > 0);
        assertTrue(detection.getEndIndex() > detection.getStartIndex());
        assertEquals(detection.getFenString().length(), detection.getLength());
    }
    
    @Test
    void testDetectEmptyString() {
        List<FenDetection> detections = detector.detectAll("");
        assertTrue(detections.isEmpty());
    }
    
    @Test
    void testDetectNullString() {
        List<FenDetection> detections = detector.detectAll(null);
        assertTrue(detections.isEmpty());
    }
}

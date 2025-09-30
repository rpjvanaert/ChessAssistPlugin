package com.github.rpjvanaert.chessassistplugin.detector;

import java.util.List;

/**
 * Interface for detecting FEN strings in text.
 */
public interface FenDetector {
    
    /**
     * Detects all FEN strings in the given text.
     * 
     * @param text the text to search
     * @return list of detected FEN strings with their positions
     */
    List<FenDetection> detectAll(String text);
    
    /**
     * Detects the first FEN string in the given text.
     * 
     * @param text the text to search
     * @return the first detected FEN string, or null if none found
     */
    default FenDetection detectFirst(String text) {
        List<FenDetection> detections = detectAll(text);
        return detections.isEmpty() ? null : detections.get(0);
    }
    
    /**
     * Checks if the text contains any FEN strings.
     * 
     * @param text the text to check
     * @return true if at least one FEN string is found
     */
    default boolean containsFen(String text) {
        return detectFirst(text) != null;
    }
}

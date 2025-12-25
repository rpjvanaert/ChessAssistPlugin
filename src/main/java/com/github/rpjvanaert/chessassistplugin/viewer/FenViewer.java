package com.github.rpjvanaert.chessassistplugin.viewer;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;

/**
 * Interface for visualizing FEN positions.
 */
public interface FenViewer {
    
    /**
     * Converts a FEN position to ASCII board representation.
     * 
     * @param position the FEN position to visualize
     * @return ASCII string representation of the board
     */
    String toAsciiBoard(FenPosition position);
    
    /**
     * Converts a FEN position to ASCII board with coordinates.
     * 
     * @param position the FEN position to visualize
     * @return ASCII string representation with rank and file labels
     */
    String toAsciiBoard(FenPosition position, boolean showCoordinates);
    
    /**
     * Gets a formatted string with all FEN components.
     * 
     * @param position the FEN position
     * @return formatted string with FEN details
     */
    String toDetailedString(FenPosition position);
}

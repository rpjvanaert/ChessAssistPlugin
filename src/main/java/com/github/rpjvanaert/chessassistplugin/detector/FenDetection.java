package com.github.rpjvanaert.chessassistplugin.detector;

import java.util.List;

/**
 * Represents a detected FEN string in text.
 */
public class FenDetection {
    private final String fenString;
    private final int startIndex;
    private final int endIndex;
    private final boolean valid;
    
    public FenDetection(String fenString, int startIndex, int endIndex, boolean valid) {
        this.fenString = fenString;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.valid = valid;
    }
    
    public String getFenString() {
        return fenString;
    }
    
    public int getStartIndex() {
        return startIndex;
    }
    
    public int getEndIndex() {
        return endIndex;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public int getLength() {
        return endIndex - startIndex;
    }
    
    @Override
    public String toString() {
        return String.format("FenDetection[%d-%d]: %s (valid=%s)", 
                           startIndex, endIndex, fenString, valid);
    }
}

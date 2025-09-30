package com.github.rpjvanaert.chessassistplugin.detector;

import com.github.rpjvanaert.chessassistplugin.core.FenValidator;
import com.github.rpjvanaert.chessassistplugin.core.FenValidatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of FenDetector using regex pattern matching.
 */
public class FenDetectorImpl implements FenDetector {
    
    private final FenValidator validator;
    
    // Pattern to match potential FEN strings
    // Matches: piece_placement active_color castling en_passant halfmove fullmove
    private static final Pattern FEN_PATTERN = Pattern.compile(
        "\\b([rnbqkpRNBQKP1-8/]+)\\s+([wb])\\s+([KQkq-]+)\\s+([a-h][36]|-)\\s+(\\d+)\\s+(\\d+)\\b"
    );
    
    public FenDetectorImpl() {
        this.validator = new FenValidatorImpl();
    }
    
    public FenDetectorImpl(FenValidator validator) {
        this.validator = validator;
    }
    
    @Override
    public List<FenDetection> detectAll(String text) {
        List<FenDetection> detections = new ArrayList<>();
        
        if (text == null || text.isEmpty()) {
            return detections;
        }
        
        Matcher matcher = FEN_PATTERN.matcher(text);
        
        while (matcher.find()) {
            String fenString = matcher.group();
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            boolean isValid = validator.isValid(fenString);
            
            detections.add(new FenDetection(fenString, startIndex, endIndex, isValid));
        }
        
        return detections;
    }
}

package com.github.rpjvanaert.chessassistplugin.core;

/**
 * Default implementation of FenParser.
 */
public class FenParserImpl implements FenParser {
    
    private final FenValidator validator;
    
    public FenParserImpl() {
        this.validator = new FenValidatorImpl();
    }
    
    public FenParserImpl(FenValidator validator) {
        this.validator = validator;
    }
    
    @Override
    public FenPosition parse(String fen) throws IllegalArgumentException {
        FenValidationResult result = validator.validate(fen);
        if (!result.isValid()) {
            throw new IllegalArgumentException("Invalid FEN: " + result.getErrorMessage());
        }
        
        String[] parts = fen.trim().split("\\s+");
        
        return new FenPositionImpl(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            Integer.parseInt(parts[4]),
            Integer.parseInt(parts[5])
        );
    }
}

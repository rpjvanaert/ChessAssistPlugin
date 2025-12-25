package com.github.rpjvanaert.chessassistplugin.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Default implementation of FenValidator.
 */
public class FenValidatorImpl implements FenValidator {
    
    private static final Pattern PIECE_PATTERN = Pattern.compile("^[pnbrqkPNBRQK1-8/]+$");
    private static final Pattern COLOR_PATTERN = Pattern.compile("^[wb]$");
    private static final Pattern CASTLING_PATTERN = Pattern.compile("^(KQ?k?q?|Qk?q?|kq?|q|-)$");
    private static final Pattern EN_PASSANT_PATTERN = Pattern.compile("^([a-h][36]|-)$");
    
    @Override
    public FenValidationResult validate(String fen) {
        if (fen == null || fen.trim().isEmpty()) {
            return FenValidationResult.invalid("FEN string cannot be null or empty");
        }
        
        String[] parts = fen.trim().split("\\s+");
        
        if (parts.length != 6) {
            return FenValidationResult.invalid(
                String.format("FEN must have 6 parts, found %d", parts.length));
        }
        
        List<String> errors = new ArrayList<>();
        
        // Validate piece placement
        if (!validatePiecePlacement(parts[0])) {
            errors.add("Invalid piece placement: " + parts[0]);
        }
        
        // Validate active color
        if (!COLOR_PATTERN.matcher(parts[1]).matches()) {
            errors.add("Invalid active color: " + parts[1] + " (must be 'w' or 'b')");
        }
        
        // Validate castling availability
        if (!CASTLING_PATTERN.matcher(parts[2]).matches()) {
            errors.add("Invalid castling availability: " + parts[2]);
        }
        
        // Validate en passant target
        if (!EN_PASSANT_PATTERN.matcher(parts[3]).matches()) {
            errors.add("Invalid en passant target: " + parts[3]);
        }
        
        // Validate halfmove clock
        try {
            int halfmove = Integer.parseInt(parts[4]);
            if (halfmove < 0) {
                errors.add("Halfmove clock must be non-negative: " + parts[4]);
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid halfmove clock: " + parts[4]);
        }
        
        // Validate fullmove number
        try {
            int fullmove = Integer.parseInt(parts[5]);
            if (fullmove < 1) {
                errors.add("Fullmove number must be at least 1: " + parts[5]);
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid fullmove number: " + parts[5]);
        }
        
        if (errors.isEmpty()) {
            return FenValidationResult.valid();
        } else {
            return FenValidationResult.invalid(errors);
        }
    }
    
    private boolean validatePiecePlacement(String placement) {
        if (!PIECE_PATTERN.matcher(placement).matches()) {
            return false;
        }
        
        String[] ranks = placement.split("/");
        if (ranks.length != 8) {
            return false;
        }
        
        for (String rank : ranks) {
            int count = 0;
            for (char c : rank.toCharArray()) {
                if (Character.isDigit(c)) {
                    count += Character.getNumericValue(c);
                } else {
                    count++;
                }
            }
            if (count != 8) {
                return false;
            }
        }
        
        return true;
    }
}

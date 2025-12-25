package com.github.rpjvanaert.chessassistplugin.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Result of FEN validation.
 */
public class FenValidationResult {
    private final boolean valid;
    private final List<String> errors;
    
    private FenValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = new ArrayList<>(errors);
    }
    
    /**
     * Creates a valid result.
     * @return valid result
     */
    public static FenValidationResult valid() {
        return new FenValidationResult(true, Collections.emptyList());
    }
    
    /**
     * Creates an invalid result with errors.
     * @param errors list of error messages
     * @return invalid result
     */
    public static FenValidationResult invalid(List<String> errors) {
        return new FenValidationResult(false, errors);
    }
    
    /**
     * Creates an invalid result with a single error.
     * @param error error message
     * @return invalid result
     */
    public static FenValidationResult invalid(String error) {
        return new FenValidationResult(false, Collections.singletonList(error));
    }
    
    /**
     * Checks if the FEN is valid.
     * @return true if valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * Returns the list of validation errors.
     * @return list of errors (empty if valid)
     */
    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
    
    /**
     * Returns a formatted error message.
     * @return error message or empty string if valid
     */
    public String getErrorMessage() {
        if (valid) {
            return "";
        }
        return String.join("; ", errors);
    }
}

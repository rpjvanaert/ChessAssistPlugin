package com.github.rpjvanaert.chessassistplugin.examples;

import com.github.rpjvanaert.chessassistplugin.core.*;
import com.github.rpjvanaert.chessassistplugin.detector.*;
import com.github.rpjvanaert.chessassistplugin.editor.*;
import com.github.rpjvanaert.chessassistplugin.generator.*;
import com.github.rpjvanaert.chessassistplugin.viewer.*;

import java.util.List;

/**
 * Usage examples demonstrating the ChessAssistPlugin modules.
 */
public class UsageExamples {
    
    public static void main(String[] args) {
        System.out.println("=== Chess Assist Plugin - Usage Examples ===\n");
        
        // Example 1: Validate a FEN string
        example1_ValidateFen();
        
        // Example 2: Parse and display a FEN position
        example2_ParseAndDisplay();
        
        // Example 3: Detect FEN strings in text
        example3_DetectFen();
        
        // Example 4: Edit a FEN position
        example4_EditFen();
        
        // Example 5: Generate FEN positions
        example5_GenerateFen();
        
        // Example 6: Complete workflow
        example6_CompleteWorkflow();
    }
    
    private static void example1_ValidateFen() {
        System.out.println("--- Example 1: Validate FEN ---");
        FenValidator validator = new FenValidatorImpl();
        
        String validFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String invalidFen = "invalid fen string";
        
        System.out.println("Valid FEN: " + validFen);
        System.out.println("Is valid: " + validator.isValid(validFen));
        
        System.out.println("\nInvalid FEN: " + invalidFen);
        FenValidationResult result = validator.validate(invalidFen);
        System.out.println("Is valid: " + result.isValid());
        System.out.println("Errors: " + result.getErrorMessage());
        System.out.println();
    }
    
    private static void example2_ParseAndDisplay() {
        System.out.println("--- Example 2: Parse and Display FEN ---");
        FenParser parser = new FenParserImpl();
        FenViewer viewer = new FenViewerImpl();
        
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenPosition position = parser.parse(fen);
        
        System.out.println(viewer.toDetailedString(position));
    }
    
    private static void example3_DetectFen() {
        System.out.println("--- Example 3: Detect FEN in Text ---");
        FenDetector detector = new FenDetectorImpl();
        
        String text = "The starting position is rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 " +
                     "and after 1.e4 c5 2.Nf3 we have rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        
        List<FenDetection> detections = detector.detectAll(text);
        System.out.println("Found " + detections.size() + " FEN strings:");
        for (int i = 0; i < detections.size(); i++) {
            FenDetection detection = detections.get(i);
            System.out.println((i + 1) + ". " + detection.getFenString());
            System.out.println("   Position: " + detection.getStartIndex() + "-" + detection.getEndIndex());
            System.out.println("   Valid: " + detection.isValid());
        }
        System.out.println();
    }
    
    private static void example4_EditFen() {
        System.out.println("--- Example 4: Edit FEN Position ---");
        FenParser parser = new FenParserImpl();
        FenEditor editor = new FenEditorImpl();
        
        String originalFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        FenPosition position = parser.parse(originalFen);
        
        System.out.println("Original: " + position.toFenString());
        
        // Change active color to black
        position = editor.setActiveColor(position, "b");
        System.out.println("After changing color: " + position.toFenString());
        
        // Increment move counters
        position = editor.setHalfmoveClock(position, 1);
        position = editor.setFullmoveNumber(position, 2);
        System.out.println("After incrementing moves: " + position.toFenString());
        System.out.println();
    }
    
    private static void example5_GenerateFen() {
        System.out.println("--- Example 5: Generate FEN Positions ---");
        FenGenerator generator = new FenGeneratorImpl();
        FenViewer viewer = new FenViewerImpl();
        
        // Generate starting position
        FenPosition startPos = generator.generateStartingPosition();
        System.out.println("Starting Position:");
        System.out.println(startPos.toFenString());
        System.out.println(viewer.toAsciiBoard(startPos, false));
        
        // Generate empty board
        FenPosition emptyBoard = generator.generateEmptyBoard();
        System.out.println("Empty Board:");
        System.out.println(emptyBoard.toFenString());
        
        // Generate custom position
        FenPosition customPos = generator.generateCustomPosition(
            "4k3/8/8/8/8/8/8/4K3", "w", "-", "-", 0, 1
        );
        System.out.println("\nCustom Position (Kings only):");
        System.out.println(customPos.toFenString());
        System.out.println(viewer.toAsciiBoard(customPos, false));
    }
    
    private static void example6_CompleteWorkflow() {
        System.out.println("--- Example 6: Complete Workflow ---");
        
        // Initialize all modules
        FenValidator validator = new FenValidatorImpl();
        FenParser parser = new FenParserImpl();
        FenEditor editor = new FenEditorImpl();
        FenViewer viewer = new FenViewerImpl();
        FenGenerator generator = new FenGeneratorImpl();
        FenDetector detector = new FenDetectorImpl();
        
        // 1. Generate a starting position
        FenPosition position = generator.generateStartingPosition();
        System.out.println("1. Generated starting position:");
        System.out.println("   " + position.toFenString());
        
        // 2. Validate it
        boolean isValid = validator.isValid(position.toFenString());
        System.out.println("2. Validation: " + (isValid ? "VALID" : "INVALID"));
        
        // 3. Edit it (simulate a move)
        position = editor.setActiveColor(position, "b");
        position = editor.setHalfmoveClock(position, 1);
        System.out.println("3. After edit: " + position.toFenString());
        
        // 4. Visualize it
        System.out.println("4. Board visualization:");
        System.out.println(viewer.toAsciiBoard(position, true));
        
        // 5. Detect it in text
        String text = "Current position: " + position.toFenString();
        List<FenDetection> detections = detector.detectAll(text);
        System.out.println("5. Detection in text: Found " + detections.size() + " FEN(s)");
        
        System.out.println("\nWorkflow completed successfully!");
    }
}

# ChessAssistPlugin API Documentation

## Table of Contents
- [Core Module API](#core-module-api)
- [Detector Module API](#detector-module-api)
- [Editor Module API](#editor-module-api)
- [Viewer Module API](#viewer-module-api)
- [Generator Module API](#generator-module-api)
- [Complete Examples](#complete-examples)

---

## Core Module API

### FenPosition Interface

Represents a chess position in FEN (Forsyth-Edwards Notation) format.

#### Methods

##### `String toFenString()`
Returns the complete FEN string representation.
- **Returns**: The FEN string (e.g., "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

##### `String getPiecePlacement()`
Returns the piece placement part of the FEN.
- **Returns**: Piece placement string (e.g., "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")

##### `String getActiveColor()`
Returns the active color.
- **Returns**: "w" for white or "b" for black

##### `String getCastlingAvailability()`
Returns the castling availability string.
- **Returns**: Combination of K, Q, k, q, or "-" (e.g., "KQkq")

##### `String getEnPassantTarget()`
Returns the en passant target square.
- **Returns**: Square in algebraic notation (e.g., "e3") or "-"

##### `int getHalfmoveClock()`
Returns the halfmove clock value.
- **Returns**: Number of halfmoves since last capture or pawn move

##### `int getFullmoveNumber()`
Returns the fullmove number.
- **Returns**: Move number (starts at 1, increments after Black's move)

#### Example
```java
FenPosition position = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
System.out.println(position.getPiecePlacement()); // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
System.out.println(position.getActiveColor());     // w
System.out.println(position.getHalfmoveClock());   // 0
```

---

### FenValidator Interface

Validates FEN string syntax and structure.

#### Methods

##### `FenValidationResult validate(String fen)`
Validates a FEN string.
- **Parameters**: 
  - `fen` - The FEN string to validate
- **Returns**: FenValidationResult containing validation status and errors

##### `boolean isValid(String fen)` (default method)
Checks if a FEN string is valid.
- **Parameters**: 
  - `fen` - The FEN string to check
- **Returns**: true if valid, false otherwise

#### Example
```java
FenValidator validator = new FenValidatorImpl();
String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

if (validator.isValid(fen)) {
    System.out.println("Valid FEN!");
}

FenValidationResult result = validator.validate(fen);
if (!result.isValid()) {
    System.out.println("Errors: " + result.getErrorMessage());
}
```

---

### FenValidationResult Class

Result of FEN validation with error details.

#### Methods

##### `boolean isValid()`
Checks if the FEN is valid.
- **Returns**: true if valid

##### `List<String> getErrors()`
Returns the list of validation errors.
- **Returns**: List of errors (empty if valid)

##### `String getErrorMessage()`
Returns a formatted error message.
- **Returns**: Error message or empty string if valid

#### Static Factory Methods

##### `static FenValidationResult valid()`
Creates a valid result.
- **Returns**: Valid result

##### `static FenValidationResult invalid(List<String> errors)`
Creates an invalid result with errors.
- **Parameters**: 
  - `errors` - List of error messages
- **Returns**: Invalid result

##### `static FenValidationResult invalid(String error)`
Creates an invalid result with a single error.
- **Parameters**: 
  - `error` - Error message
- **Returns**: Invalid result

---

### FenParser Interface

Parses FEN strings into FenPosition objects.

#### Methods

##### `FenPosition parse(String fen)`
Parses a FEN string into a FenPosition object.
- **Parameters**: 
  - `fen` - The FEN string to parse
- **Returns**: The parsed FenPosition
- **Throws**: IllegalArgumentException if the FEN string is invalid

##### `FenPosition tryParse(String fen)` (default method)
Attempts to parse a FEN string, returning null if invalid.
- **Parameters**: 
  - `fen` - The FEN string to parse
- **Returns**: The parsed FenPosition or null if invalid

#### Example
```java
FenParser parser = new FenParserImpl();

// Parse with exception handling
try {
    FenPosition position = parser.parse(fen);
    System.out.println("Parsed: " + position.toFenString());
} catch (IllegalArgumentException e) {
    System.err.println("Invalid FEN: " + e.getMessage());
}

// Safe parsing
FenPosition position = parser.tryParse(fen);
if (position != null) {
    System.out.println("Parsed successfully");
}
```

---

## Detector Module API

### FenDetector Interface

Detects FEN strings in text.

#### Methods

##### `List<FenDetection> detectAll(String text)`
Detects all FEN strings in the given text.
- **Parameters**: 
  - `text` - The text to search
- **Returns**: List of detected FEN strings with their positions

##### `FenDetection detectFirst(String text)` (default method)
Detects the first FEN string in the given text.
- **Parameters**: 
  - `text` - The text to search
- **Returns**: The first detected FEN string, or null if none found

##### `boolean containsFen(String text)` (default method)
Checks if the text contains any FEN strings.
- **Parameters**: 
  - `text` - The text to check
- **Returns**: true if at least one FEN string is found

#### Example
```java
FenDetector detector = new FenDetectorImpl();
String text = "Starting position: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

List<FenDetection> detections = detector.detectAll(text);
for (FenDetection detection : detections) {
    System.out.println("Found: " + detection.getFenString());
    System.out.println("At position: " + detection.getStartIndex());
    System.out.println("Valid: " + detection.isValid());
}

if (detector.containsFen(text)) {
    System.out.println("Text contains FEN!");
}
```

---

### FenDetection Class

Represents a detected FEN string in text.

#### Methods

##### `String getFenString()`
Returns the detected FEN string.

##### `int getStartIndex()`
Returns the start index in the original text.

##### `int getEndIndex()`
Returns the end index in the original text.

##### `boolean isValid()`
Returns whether the detected FEN is valid.

##### `int getLength()`
Returns the length of the detected FEN string.

---

## Editor Module API

### FenEditor Interface

Provides immutable editing operations for FEN positions.

#### Methods

##### `FenPosition setPiecePlacement(FenPosition position, String piecePlacement)`
Sets the piece placement part of the FEN.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `piecePlacement` - New piece placement
- **Returns**: New FEN position with updated piece placement

##### `FenPosition setActiveColor(FenPosition position, String activeColor)`
Sets the active color.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `activeColor` - New active color ("w" or "b")
- **Returns**: New FEN position with updated active color

##### `FenPosition setCastlingAvailability(FenPosition position, String castling)`
Sets the castling availability.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `castling` - New castling availability
- **Returns**: New FEN position with updated castling

##### `FenPosition setEnPassantTarget(FenPosition position, String enPassant)`
Sets the en passant target square.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `enPassant` - New en passant target
- **Returns**: New FEN position with updated en passant

##### `FenPosition setHalfmoveClock(FenPosition position, int halfmove)`
Sets the halfmove clock.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `halfmove` - New halfmove clock value
- **Returns**: New FEN position with updated halfmove clock

##### `FenPosition setFullmoveNumber(FenPosition position, int fullmove)`
Sets the fullmove number.
- **Parameters**: 
  - `position` - The FEN position to modify
  - `fullmove` - New fullmove number
- **Returns**: New FEN position with updated fullmove number

#### Example
```java
FenEditor editor = new FenEditorImpl();
FenPosition position = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

// All operations return new instances
position = editor.setActiveColor(position, "b");
position = editor.setHalfmoveClock(position, 1);
position = editor.setFullmoveNumber(position, 2);

System.out.println(position.toFenString());
// Output: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 1 2
```

---

## Viewer Module API

### FenViewer Interface

Visualizes FEN positions in various formats.

#### Methods

##### `String toAsciiBoard(FenPosition position)`
Converts a FEN position to ASCII board representation with coordinates.
- **Parameters**: 
  - `position` - The FEN position to visualize
- **Returns**: ASCII string representation of the board

##### `String toAsciiBoard(FenPosition position, boolean showCoordinates)`
Converts a FEN position to ASCII board representation.
- **Parameters**: 
  - `position` - The FEN position to visualize
  - `showCoordinates` - Whether to show rank and file labels
- **Returns**: ASCII string representation

##### `String toDetailedString(FenPosition position)`
Gets a formatted string with all FEN components.
- **Parameters**: 
  - `position` - The FEN position
- **Returns**: Formatted string with FEN details and board

#### Example
```java
FenViewer viewer = new FenViewerImpl();
FenPosition position = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

// Simple board
String board = viewer.toAsciiBoard(position, false);
System.out.println(board);

// Board with coordinates
String boardWithCoords = viewer.toAsciiBoard(position, true);
System.out.println(boardWithCoords);

// Detailed view
String details = viewer.toDetailedString(position);
System.out.println(details);
```

---

## Generator Module API

### FenGenerator Interface

Generates FEN positions.

#### Methods

##### `FenPosition generateStartingPosition()`
Generates a FEN position for the standard starting position.
- **Returns**: FEN position for the initial chess setup

##### `FenPosition generateEmptyBoard()`
Generates an empty board FEN position.
- **Returns**: FEN position with no pieces

##### `FenPosition generateCustomPosition(String piecePlacement, String activeColor, String castling, String enPassant, int halfmove, int fullmove)`
Generates a custom FEN position from components.
- **Parameters**: 
  - `piecePlacement` - Piece placement string
  - `activeColor` - Active color ("w" or "b")
  - `castling` - Castling availability
  - `enPassant` - En passant target square
  - `halfmove` - Halfmove clock
  - `fullmove` - Fullmove number
- **Returns**: Generated FEN position

#### Example
```java
FenGenerator generator = new FenGeneratorImpl();

// Standard starting position
FenPosition start = generator.generateStartingPosition();
System.out.println(start.toFenString());
// Output: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1

// Empty board
FenPosition empty = generator.generateEmptyBoard();
System.out.println(empty.toFenString());
// Output: 8/8/8/8/8/8/8/8 w - - 0 1

// Custom position (kings only)
FenPosition custom = generator.generateCustomPosition(
    "4k3/8/8/8/8/8/8/4K3", "w", "-", "-", 0, 1
);
System.out.println(custom.toFenString());
// Output: 4k3/8/8/8/8/8/8/4K3 w - - 0 1
```

---

## Complete Examples

### Example 1: FEN Validation and Error Handling

```java
FenValidator validator = new FenValidatorImpl();

String[] fens = {
    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", // Valid
    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR x KQkq - 0 1", // Invalid color
    "invalid fen"                                                 // Invalid format
};

for (String fen : fens) {
    FenValidationResult result = validator.validate(fen);
    if (result.isValid()) {
        System.out.println("✓ Valid: " + fen);
    } else {
        System.out.println("✗ Invalid: " + fen);
        System.out.println("  Errors: " + result.getErrorMessage());
    }
}
```

### Example 2: FEN Detection in Code

```java
FenDetector detector = new FenDetectorImpl();
String code = """
    public class ChessGame {
        private static final String STARTING_FEN = 
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
            
        public void loadPosition() {
            // After 1.e4 c5 2.Nf3
            String sicilian = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        }
    }
    """;

List<FenDetection> detections = detector.detectAll(code);
System.out.println("Found " + detections.size() + " FEN strings in code:");
for (FenDetection d : detections) {
    System.out.println("  - " + d.getFenString().substring(0, 20) + "...");
}
```

### Example 3: FEN Position Editing Chain

```java
FenParser parser = new FenParserImpl();
FenEditor editor = new FenEditorImpl();

// Start with initial position
FenPosition pos = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

// Simulate a move: change color and update counters
pos = editor.setActiveColor(pos, "b");
pos = editor.setHalfmoveClock(pos, 1);

// Simulate another move
pos = editor.setActiveColor(pos, "w");
pos = editor.setHalfmoveClock(pos, 2);
pos = editor.setFullmoveNumber(pos, 2);

System.out.println("Final position: " + pos.toFenString());
```

### Example 4: Complete Workflow

```java
// Initialize all components
FenGenerator generator = new FenGeneratorImpl();
FenValidator validator = new FenValidatorImpl();
FenParser parser = new FenParserImpl();
FenEditor editor = new FenEditorImpl();
FenViewer viewer = new FenViewerImpl();

// 1. Generate starting position
FenPosition position = generator.generateStartingPosition();

// 2. Validate
if (validator.isValid(position.toFenString())) {
    System.out.println("Position is valid");
}

// 3. Make edits
position = editor.setActiveColor(position, "b");
position = editor.setHalfmoveClock(position, 1);

// 4. Visualize
System.out.println(viewer.toDetailedString(position));

// 5. Export
String fenString = position.toFenString();
System.out.println("Export: " + fenString);
```

---

## Error Handling

### Validation Errors
The validator provides detailed error messages:

- "FEN string cannot be null or empty"
- "FEN must have 6 parts, found X"
- "Invalid piece placement: ..."
- "Invalid active color: ... (must be 'w' or 'b')"
- "Invalid castling availability: ..."
- "Invalid en passant target: ..."
- "Halfmove clock must be non-negative: ..."
- "Fullmove number must be at least 1: ..."

### Parser Exceptions
The parser throws `IllegalArgumentException` with descriptive messages:

```java
try {
    FenPosition pos = parser.parse(invalidFen);
} catch (IllegalArgumentException e) {
    System.err.println("Parse error: " + e.getMessage());
}
```

### Safe Operations
Use `tryParse()` for safe parsing without exceptions:

```java
FenPosition pos = parser.tryParse(possiblyInvalidFen);
if (pos == null) {
    // Handle invalid FEN
}
```

# ChessAssistPlugin

A modular IntelliJ IDEA plugin for working with chess FEN (Forsyth-Edwards Notation) strings. This plugin provides a comprehensive set of tools to detect, validate, parse, edit, view, and generate FEN positions.

## Features

- **FEN Detection**: Automatically detect FEN strings in text/code
- **FEN Validation**: Validate FEN syntax and structure
- **FEN Parsing**: Parse FEN strings into structured objects
- **FEN Editing**: Modify FEN positions with immutable operations
- **FEN Viewing**: Visualize chess positions with ASCII board representations
- **FEN Generation**: Generate standard and custom FEN positions

## Architecture

The plugin is built with a modular architecture, with each feature implemented as a separate module:

### Core Module (`com.github.rpjvanaert.chessassistplugin.core`)
- **`FenPosition`**: Interface representing a chess position
- **`FenPositionImpl`**: Default implementation of FenPosition
- **`FenValidator`**: Interface for FEN validation
- **`FenValidatorImpl`**: Validates FEN syntax and structure
- **`FenParser`**: Interface for parsing FEN strings
- **`FenParserImpl`**: Parses FEN strings into FenPosition objects
- **`FenValidationResult`**: Represents validation results with error messages

### Detector Module (`com.github.rpjvanaert.chessassistplugin.detector`)
- **`FenDetector`**: Interface for detecting FEN strings in text
- **`FenDetectorImpl`**: Regex-based FEN detection
- **`FenDetection`**: Represents a detected FEN with position information

### Editor Module (`com.github.rpjvanaert.chessassistplugin.editor`)
- **`FenEditor`**: Interface for editing FEN positions
- **`FenEditorImpl`**: Immutable FEN editing operations

### Viewer Module (`com.github.rpjvanaert.chessassistplugin.viewer`)
- **`FenViewer`**: Interface for visualizing FEN positions
- **`FenViewerImpl`**: ASCII board representation and detailed formatting

### Generator Module (`com.github.rpjvanaert.chessassistplugin.generator`)
- **`FenGenerator`**: Interface for generating FEN positions
- **`FenGeneratorImpl`**: Generates starting position, empty board, and custom positions

## Usage Examples

### Example 1: Validate a FEN String

```java
FenValidator validator = new FenValidatorImpl();
String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

FenValidationResult result = validator.validate(fen);
if (result.isValid()) {
    System.out.println("Valid FEN!");
} else {
    System.out.println("Errors: " + result.getErrorMessage());
}
```

### Example 2: Parse and Display a FEN Position

```java
FenParser parser = new FenParserImpl();
FenViewer viewer = new FenViewerImpl();

String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
FenPosition position = parser.parse(fen);

System.out.println(viewer.toDetailedString(position));
```

### Example 3: Detect FEN Strings in Text

```java
FenDetector detector = new FenDetectorImpl();
String text = "The starting position is rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

List<FenDetection> detections = detector.detectAll(text);
for (FenDetection detection : detections) {
    System.out.println("Found FEN: " + detection.getFenString());
    System.out.println("Valid: " + detection.isValid());
}
```

### Example 4: Edit a FEN Position

```java
FenParser parser = new FenParserImpl();
FenEditor editor = new FenEditorImpl();

String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
FenPosition position = parser.parse(fen);

// Change active color to black
position = editor.setActiveColor(position, "b");

// Increment move counters
position = editor.setHalfmoveClock(position, 1);
position = editor.setFullmoveNumber(position, 2);

System.out.println(position.toFenString());
```

### Example 5: Generate FEN Positions

```java
FenGenerator generator = new FenGeneratorImpl();

// Generate starting position
FenPosition startPos = generator.generateStartingPosition();

// Generate empty board
FenPosition emptyBoard = generator.generateEmptyBoard();

// Generate custom position
FenPosition customPos = generator.generateCustomPosition(
    "4k3/8/8/8/8/8/8/4K3", "w", "-", "-", 0, 1
);
```

For more examples, see `UsageExamples.java` in the examples package.

## Building the Plugin

### Prerequisites
- JDK 17 or higher
- Gradle 7.0 or higher

### Build Commands

```bash
# Build the plugin
./gradlew build

# Run tests
./gradlew test

# Build plugin distribution
./gradlew buildPlugin
```

## Testing

The plugin includes comprehensive unit tests for all modules:

- **Core Module Tests**: FEN validation, parsing, and position handling
- **Detector Tests**: FEN detection in various text formats
- **Editor Tests**: Immutable editing operations
- **Viewer Tests**: ASCII board visualization
- **Generator Tests**: FEN position generation

Run tests with:
```bash
./gradlew test
```

## FEN Format

FEN (Forsyth-Edwards Notation) is a standard notation for describing chess positions. It consists of six fields separated by spaces:

1. **Piece Placement**: Describes the position of pieces on the board (from rank 8 to rank 1)
   - Uppercase letters for white pieces (PNBRQK)
   - Lowercase letters for black pieces (pnbrqk)
   - Numbers represent empty squares (1-8)
   - Slashes (/) separate ranks

2. **Active Color**: `w` for white, `b` for black

3. **Castling Availability**: Combination of K, Q, k, q, or `-` if none available

4. **En Passant Target**: Target square in algebraic notation (e.g., `e3`) or `-`

5. **Halfmove Clock**: Number of halfmoves since last capture or pawn advance

6. **Fullmove Number**: Move number (starts at 1, increments after Black's move)

Example: `rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1`

## Extension Points

The plugin is designed for extensibility:

- All modules use interface-based design
- Implementations can be easily swapped or extended
- Custom validators, parsers, and viewers can be added
- Future integration with IntelliJ IDEA actions and inspections

## License

MIT License - See LICENSE file for details.

## Contributing

Contributions are welcome! Please ensure:
- All tests pass
- New features include unit tests
- Code follows existing patterns and style
- Documentation is updated

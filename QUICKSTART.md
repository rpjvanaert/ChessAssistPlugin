# ChessAssistPlugin Quick Start Guide

## Installation & Setup

### Build from Source
```bash
git clone https://github.com/rpjvanaert/ChessAssistPlugin.git
cd ChessAssistPlugin
./gradlew build
```

### Run Tests
```bash
./gradlew test
```

### Run Examples
```bash
./gradlew compileJava
java -cp "build/classes/java/main" com.github.rpjvanaert.chessassistplugin.examples.UsageExamples
```

## Quick Usage Examples

### 1. Validate a FEN String
```java
import com.github.rpjvanaert.chessassistplugin.core.*;

FenValidator validator = new FenValidatorImpl();
String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

if (validator.isValid(fen)) {
    System.out.println("Valid FEN!");
}
```

### 2. Parse a FEN String
```java
import com.github.rpjvanaert.chessassistplugin.core.*;

FenParser parser = new FenParserImpl();
String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
FenPosition position = parser.parse(fen);

System.out.println("Active color: " + position.getActiveColor());
System.out.println("Castling: " + position.getCastlingAvailability());
```

### 3. Detect FEN in Text
```java
import com.github.rpjvanaert.chessassistplugin.detector.*;

FenDetector detector = new FenDetectorImpl();
String text = "Starting position: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

List<FenDetection> detections = detector.detectAll(text);
System.out.println("Found " + detections.size() + " FEN strings");
```

### 4. Edit a FEN Position
```java
import com.github.rpjvanaert.chessassistplugin.core.*;
import com.github.rpjvanaert.chessassistplugin.editor.*;

FenParser parser = new FenParserImpl();
FenEditor editor = new FenEditorImpl();

FenPosition pos = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
pos = editor.setActiveColor(pos, "b");
pos = editor.setHalfmoveClock(pos, 1);

System.out.println(pos.toFenString());
```

### 5. Visualize a FEN Position
```java
import com.github.rpjvanaert.chessassistplugin.core.*;
import com.github.rpjvanaert.chessassistplugin.viewer.*;

FenParser parser = new FenParserImpl();
FenViewer viewer = new FenViewerImpl();

FenPosition pos = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
System.out.println(viewer.toAsciiBoard(pos));
```

### 6. Generate FEN Positions
```java
import com.github.rpjvanaert.chessassistplugin.generator.*;

FenGenerator generator = new FenGeneratorImpl();

// Starting position
FenPosition start = generator.generateStartingPosition();

// Empty board
FenPosition empty = generator.generateEmptyBoard();

// Custom position
FenPosition custom = generator.generateCustomPosition(
    "4k3/8/8/8/8/8/8/4K3", "w", "-", "-", 0, 1
);
```

## Module Overview

| Module | Purpose | Key Classes |
|--------|---------|-------------|
| **core** | FEN data structures & validation | `FenPosition`, `FenValidator`, `FenParser` |
| **detector** | Find FEN strings in text | `FenDetector`, `FenDetection` |
| **editor** | Modify FEN positions | `FenEditor` |
| **viewer** | Visualize positions | `FenViewer` |
| **generator** | Create FEN positions | `FenGenerator` |

## Common Use Cases

### Use Case 1: Validate User Input
```java
FenValidator validator = new FenValidatorImpl();
FenValidationResult result = validator.validate(userInput);

if (!result.isValid()) {
    for (String error : result.getErrors()) {
        System.err.println("Error: " + error);
    }
}
```

### Use Case 2: Extract FEN from Code
```java
FenDetector detector = new FenDetectorImpl();
String sourceCode = readFile("ChessGame.java");

for (FenDetection detection : detector.detectAll(sourceCode)) {
    if (detection.isValid()) {
        System.out.println("Found FEN at line: " + detection.getStartIndex());
    }
}
```

### Use Case 3: Simulate a Chess Move
```java
FenParser parser = new FenParserImpl();
FenEditor editor = new FenEditorImpl();

// Start position
FenPosition pos = parser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

// Simulate move: change piece placement and update position
pos = editor.setPiecePlacement(pos, "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR");
pos = editor.setActiveColor(pos, "b");
pos = editor.setHalfmoveClock(pos, 0);

System.out.println("After 1.e4: " + pos.toFenString());
```

### Use Case 4: Display Position Details
```java
FenParser parser = new FenParserImpl();
FenViewer viewer = new FenViewerImpl();

FenPosition pos = parser.parse(fenString);
System.out.println(viewer.toDetailedString(pos));
```

## FEN Format Reference

A FEN string has 6 space-separated fields:

```
rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
│                                           │ │    │ │ │
│                                           │ │    │ │ └─ Fullmove number
│                                           │ │    │ └─── Halfmove clock  
│                                           │ │    └───── En passant square
│                                           │ └────────── Castling rights
│                                           └──────────── Active color
└──────────────────────────────────────────────────────── Piece placement
```

### Piece Symbols
- **Uppercase**: White pieces (P, N, B, R, Q, K)
- **Lowercase**: Black pieces (p, n, b, r, q, k)
- **Numbers**: Empty squares (1-8)
- **Slash**: Rank separator

## Testing Your Code

### Unit Test Template
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyTest {
    @Test
    void testFenValidation() {
        FenValidator validator = new FenValidatorImpl();
        assertTrue(validator.isValid("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
    }
}
```

## Troubleshooting

### Build Issues
```bash
# Clean build
./gradlew clean build

# Refresh dependencies
./gradlew build --refresh-dependencies
```

### Test Failures
```bash
# Run specific test
./gradlew test --tests "FenValidatorImplTest"

# Run with stack trace
./gradlew test --stacktrace
```

## Next Steps

1. **Read the [API Documentation](API.md)** for detailed method documentation
2. **Review [Architecture](ARCHITECTURE.md)** to understand the design
3. **Check [Contributing Guidelines](CONTRIBUTING.md)** to contribute
4. **Explore [Usage Examples](src/main/java/com/github/rpjvanaert/chessassistplugin/examples/UsageExamples.java)**

## Need Help?

- 📖 [Full Documentation](README.md)
- 🔧 [API Reference](API.md)
- 🏗️ [Architecture Guide](ARCHITECTURE.md)
- 🤝 [Contributing](CONTRIBUTING.md)
- 🐛 [Report Issues](https://github.com/rpjvanaert/ChessAssistPlugin/issues)

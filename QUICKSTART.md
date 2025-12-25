# ChessAssistPlugin Quick Start Guide

## Installation & Setup

### Option 1: Install Plugin Locally for Testing

#### Step 1: Build the Plugin
```bash
git clone https://github.com/rpjvanaert/ChessAssistPlugin.git
cd ChessAssistPlugin
./gradlew buildPlugin
```

This will create a plugin distribution ZIP file in `build/distributions/`.

#### Step 2: Install in IntelliJ IDEA

**Method A: Install from Disk**
1. Open IntelliJ IDEA
2. Go to `File > Settings` (or `IntelliJ IDEA > Preferences` on macOS)
3. Navigate to `Plugins`
4. Click the ⚙️ (gear) icon
5. Select `Install Plugin from Disk...`
6. Browse to `build/distributions/ChessAssistPlugin-1.0.0.zip`
7. Click `OK` and restart IntelliJ IDEA

**Method B: Run in Development Mode**
```bash
./gradlew runIde
```
This will launch a new IntelliJ IDEA instance with your plugin pre-installed for testing.

#### Step 3: Verify Installation
1. After restart, go to `File > Settings > Plugins`
2. Look for "Chess Assist Plugin" in the installed plugins list
3. The plugin should be enabled

### Option 2: Build from Source (Library Mode)
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

# Clean plugin build
./gradlew clean buildPlugin
```

### Test Failures
```bash
# Run specific test
./gradlew test --tests "FenValidatorImplTest"

# Run with stack trace
./gradlew test --stacktrace
```

### Plugin Development & Debugging

#### Available Gradle Tasks for Plugin Development
```bash
# Build the plugin distribution
./gradlew buildPlugin

# Run IntelliJ with the plugin in development mode
./gradlew runIde

# Run plugin verifier (checks compatibility)
./gradlew runPluginVerifier

# Build and verify the plugin
./gradlew verifyPlugin

# Clean plugin artifacts
./gradlew clean
```

#### Debug the Plugin
1. Run: `./gradlew runIde --debug-jvm`
2. In your main IntelliJ IDEA, create a Remote JVM Debug configuration:
   - Host: `localhost`
   - Port: `5005`
3. Set breakpoints in your plugin code
4. Start the debug configuration

#### Hot Reload During Development
After making changes:
```bash
# Rebuild and run
./gradlew clean buildPlugin runIde
```

#### Check Plugin Structure
```bash
# Verify plugin.xml and structure
./gradlew verifyPlugin

# View plugin structure
unzip -l build/distributions/ChessAssistPlugin-1.0.0.zip
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

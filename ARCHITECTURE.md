# ChessAssistPlugin Architecture

## Module Overview

The ChessAssistPlugin is built with a modular, interface-driven architecture that separates concerns and enables easy extensibility.

```
┌─────────────────────────────────────────────────────────────┐
│                     ChessAssistPlugin                        │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐         │
│  │   Detector  │  │   Editor    │  │   Viewer    │         │
│  │   Module    │  │   Module    │  │   Module    │         │
│  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘         │
│         │                 │                 │                 │
│         └─────────────────┴─────────────────┘                │
│                           │                                   │
│                  ┌────────▼────────┐                         │
│                  │   Core Module   │                         │
│                  │                 │                         │
│                  │  - FenPosition  │                         │
│                  │  - FenParser    │                         │
│                  │  - FenValidator │                         │
│                  └─────────────────┘                         │
│                           │                                   │
│                  ┌────────▼────────┐                         │
│                  │   Generator     │                         │
│                  │   Module        │                         │
│                  └─────────────────┘                         │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

## Module Descriptions

### Core Module (`core`)
**Purpose**: Provides fundamental FEN data structures and operations.

**Components**:
- **FenPosition** (Interface): Represents a chess position in FEN format
- **FenPositionImpl**: Immutable implementation of FenPosition
- **FenValidator** (Interface): Validates FEN string syntax and semantics
- **FenValidatorImpl**: Regex-based validator with detailed error reporting
- **FenParser** (Interface): Parses FEN strings into FenPosition objects
- **FenParserImpl**: Default parser implementation
- **FenValidationResult**: Contains validation status and error messages

**Dependencies**: None (foundation module)

### Detector Module (`detector`)
**Purpose**: Detects FEN strings within text and code.

**Components**:
- **FenDetector** (Interface): Detects FEN patterns in text
- **FenDetectorImpl**: Regex-based detection with position tracking
- **FenDetection**: Represents a detected FEN with location information

**Dependencies**: Core Module (for validation)

### Editor Module (`editor`)
**Purpose**: Provides immutable editing operations for FEN positions.

**Components**:
- **FenEditor** (Interface): Methods for modifying FEN positions
- **FenEditorImpl**: Creates new FenPosition objects with modifications

**Dependencies**: Core Module

**Design Pattern**: Immutable object pattern - all edits return new instances

### Viewer Module (`viewer`)
**Purpose**: Visualizes FEN positions in various formats.

**Components**:
- **FenViewer** (Interface): Methods for rendering FEN positions
- **FenViewerImpl**: ASCII board representation with coordinates

**Dependencies**: Core Module

**Output Formats**:
- ASCII board with/without coordinates
- Detailed position information
- Text-based visualization

### Generator Module (`generator`)
**Purpose**: Creates FEN positions from templates and custom specifications.

**Components**:
- **FenGenerator** (Interface): Factory methods for FEN positions
- **FenGeneratorImpl**: Generates standard and custom positions

**Dependencies**: Core Module

**Presets**:
- Starting chess position
- Empty board
- Custom position builder

## Design Principles

### 1. Interface-Driven Design
All modules expose interface contracts, allowing for:
- Easy mocking in tests
- Multiple implementations
- Clear API boundaries
- Loose coupling

### 2. Immutability
- FenPosition objects are immutable
- Edits create new instances
- Thread-safe by design

### 3. Separation of Concerns
Each module has a single, well-defined responsibility:
- Core: Data and validation
- Detector: Pattern matching
- Editor: Modifications
- Viewer: Visualization
- Generator: Creation

### 4. Dependency Flow
```
Generator ─┐
           ├─→ Core ←── Detector
Editor ────┤           
Viewer ────┘
```

No circular dependencies; all modules depend only on Core.

### 5. Extensibility Points

#### Custom Validators
Implement `FenValidator` for specialized validation:
```java
public class StrictFenValidator implements FenValidator {
    // Add custom validation rules
}
```

#### Custom Viewers
Implement `FenViewer` for different output formats:
```java
public class HtmlFenViewer implements FenViewer {
    // Generate HTML board representation
}
```

#### Custom Detectors
Implement `FenDetector` for context-aware detection:
```java
public class CommentAwareFenDetector implements FenDetector {
    // Detect only in comments or strings
}
```

## Testing Strategy

### Unit Tests
- Each module has comprehensive unit tests
- Interfaces enable easy mocking
- Test coverage includes edge cases and error conditions

### Test Structure
```
src/test/java/
├── core/
│   ├── FenValidatorImplTest.java
│   └── FenParserImplTest.java
├── detector/
│   └── FenDetectorImplTest.java
├── editor/
│   └── FenEditorImplTest.java
├── viewer/
│   └── FenViewerImplTest.java
└── generator/
    └── FenGeneratorImplTest.java
```

### Integration
The `UsageExamples` class demonstrates full integration workflows.

## Future Extensions

### Planned Enhancements
1. **IntelliJ IDEA Integration**
   - Code inspections for invalid FEN strings
   - Quick fixes for common FEN errors
   - Live preview of FEN positions
   - Intention actions for FEN manipulation

2. **Additional Validators**
   - Legal position validator (check if position is reachable)
   - Opening position detector

3. **Enhanced Viewers**
   - Unicode chess symbols
   - HTML/SVG output
   - Chess diagram export

4. **Advanced Editing**
   - Move generation from FEN
   - FEN diff/comparison
   - Batch editing operations

## Package Structure

```
com.github.rpjvanaert.chessassistplugin/
├── core/                    # Core FEN functionality
│   ├── FenPosition.java
│   ├── FenPositionImpl.java
│   ├── FenValidator.java
│   ├── FenValidatorImpl.java
│   ├── FenParser.java
│   ├── FenParserImpl.java
│   └── FenValidationResult.java
│
├── detector/               # FEN detection
│   ├── FenDetector.java
│   ├── FenDetectorImpl.java
│   └── FenDetection.java
│
├── editor/                 # FEN editing
│   ├── FenEditor.java
│   └── FenEditorImpl.java
│
├── viewer/                 # FEN visualization
│   ├── FenViewer.java
│   └── FenViewerImpl.java
│
├── generator/              # FEN generation
│   ├── FenGenerator.java
│   └── FenGeneratorImpl.java
│
└── examples/              # Usage examples
    └── UsageExamples.java
```

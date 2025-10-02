# Contributing to ChessAssistPlugin

Thank you for your interest in contributing to ChessAssistPlugin! This document provides guidelines and instructions for contributing to the project.

## Table of Contents
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Code Style](#code-style)
- [Testing](#testing)
- [Submitting Changes](#submitting-changes)
- [Module Development](#module-development)

## Getting Started

### Prerequisites
- JDK 17 or higher
- Gradle 8.5 or higher (wrapper included)
- Git
- Your favorite Java IDE (IntelliJ IDEA recommended)

### Fork and Clone

1. Fork the repository on GitHub
2. Clone your fork:
```bash
git clone https://github.com/YOUR_USERNAME/ChessAssistPlugin.git
cd ChessAssistPlugin
```

3. Add the upstream repository:
```bash
git remote add upstream https://github.com/rpjvanaert/ChessAssistPlugin.git
```

## Development Setup

### Build the Project

```bash
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

### IDE Setup (IntelliJ IDEA)

1. Open the project in IntelliJ IDEA
2. The IDE should automatically detect the Gradle build
3. Wait for dependencies to download
4. Run tests from the IDE to verify setup

## Code Style

### General Guidelines

1. **Follow existing patterns**: Match the style of existing code
2. **Use meaningful names**: Classes, methods, and variables should have descriptive names
3. **Write documentation**: All public APIs must have JavaDoc comments
4. **Keep it simple**: Prefer clarity over cleverness

### Java Code Style

```java
/**
 * JavaDoc for all public classes and methods.
 * Explain what the class/method does.
 */
public class ExampleClass {
    
    // Constants in UPPER_SNAKE_CASE
    private static final String DEFAULT_VALUE = "example";
    
    // Fields with clear names
    private final String fieldName;
    
    /**
     * Constructor documentation.
     * @param fieldName the field name
     */
    public ExampleClass(String fieldName) {
        this.fieldName = fieldName;
    }
    
    /**
     * Method documentation.
     * @param input the input parameter
     * @return the result
     */
    public String methodName(String input) {
        // Implementation
        return input;
    }
}
```

### Package Organization

```
com.github.rpjvanaert.chessassistplugin/
├── core/           # Core functionality - interfaces and basic implementations
├── detector/       # FEN detection
├── editor/         # FEN editing
├── viewer/         # FEN visualization  
├── generator/      # FEN generation
└── [newmodule]/    # New modules follow same pattern
```

## Testing

### Test Requirements

1. **All new features must have tests**
2. **Maintain or improve code coverage**
3. **Test edge cases and error conditions**
4. **Follow existing test patterns**

### Test Structure

```java
package com.github.rpjvanaert.chessassistplugin.module;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {
    
    private MyClass instance;
    
    @BeforeEach
    void setUp() {
        instance = new MyClass();
    }
    
    @Test
    void testFeatureWorksCorrectly() {
        // Arrange
        String input = "test";
        
        // Act
        String result = instance.doSomething(input);
        
        // Assert
        assertEquals("expected", result);
    }
    
    @Test
    void testErrorHandling() {
        assertThrows(IllegalArgumentException.class, 
            () -> instance.doSomething(null));
    }
}
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for a specific module
./gradlew test --tests "com.github.rpjvanaert.chessassistplugin.core.*"

# Run with coverage (if configured)
./gradlew test jacocoTestReport
```

## Submitting Changes

### Pull Request Process

1. **Create a feature branch**:
```bash
git checkout -b feature/your-feature-name
```

2. **Make your changes**:
   - Write code
   - Add tests
   - Update documentation

3. **Commit your changes**:
```bash
git add .
git commit -m "feat: add new feature"
```

4. **Keep your branch updated**:
```bash
git fetch upstream
git rebase upstream/main
```

5. **Push to your fork**:
```bash
git push origin feature/your-feature-name
```

6. **Create a Pull Request**:
   - Go to GitHub
   - Click "New Pull Request"
   - Describe your changes
   - Reference any related issues

### Commit Message Convention

Use conventional commits format:

```
<type>: <description>

[optional body]

[optional footer]
```

Types:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `test`: Adding or updating tests
- `refactor`: Code refactoring
- `style`: Code style changes (formatting, etc.)
- `chore`: Build process or auxiliary tool changes

Examples:
```
feat: add FEN normalization support
fix: correct castling validation for Chess960
docs: update API documentation for FenEditor
test: add edge case tests for FenDetector
```

## Module Development

### Adding a New Module

1. **Create package structure**:
```
src/main/java/com/github/rpjvanaert/chessassistplugin/newmodule/
src/test/java/com/github/rpjvanaert/chessassistplugin/newmodule/
```

2. **Define interfaces**:
```java
package com.github.rpjvanaert.chessassistplugin.newmodule;

/**
 * Interface for [module purpose].
 */
public interface NewFeature {
    /**
     * [Method documentation]
     */
    ResultType doSomething(InputType input);
}
```

3. **Implement classes**:
```java
package com.github.rpjvanaert.chessassistplugin.newmodule;

/**
 * Default implementation of NewFeature.
 */
public class NewFeatureImpl implements NewFeature {
    @Override
    public ResultType doSomething(InputType input) {
        // Implementation
    }
}
```

4. **Write tests**:
```java
package com.github.rpjvanaert.chessassistplugin.newmodule;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewFeatureImplTest {
    @Test
    void testDoSomething() {
        // Test implementation
    }
}
```

5. **Add usage examples**:
```java
// Add example to UsageExamples.java
private static void exampleN_NewFeature() {
    System.out.println("--- Example N: New Feature ---");
    NewFeature feature = new NewFeatureImpl();
    // Demonstrate usage
}
```

6. **Update documentation**:
   - Add to README.md
   - Add to API.md
   - Add to ARCHITECTURE.md

### Extending Existing Modules

When adding functionality to existing modules:

1. **Check existing patterns**
2. **Maintain backward compatibility**
3. **Update all related documentation**
4. **Add comprehensive tests**
5. **Consider interface changes carefully**

### Design Principles to Follow

1. **Interface-Driven**: Always define interfaces for new features
2. **Immutability**: Prefer immutable data structures
3. **Single Responsibility**: Each class should have one clear purpose
4. **Dependency Injection**: Use constructor injection for dependencies
5. **Fail Fast**: Validate inputs and fail quickly with clear messages

## Documentation

### What to Document

1. **Public APIs**: All public classes, interfaces, and methods
2. **Complex Logic**: Non-obvious implementations
3. **Usage Examples**: How to use new features
4. **Breaking Changes**: Clearly document any breaking changes

### Documentation Files

- **README.md**: Overview and quick start
- **API.md**: Detailed API documentation
- **ARCHITECTURE.md**: System design and module structure
- **CONTRIBUTING.md**: This file

### JavaDoc Guidelines

```java
/**
 * Brief one-line description.
 * 
 * Detailed description if needed. Can span
 * multiple lines and paragraphs.
 * 
 * @param paramName description of parameter
 * @return description of return value
 * @throws ExceptionType when and why it's thrown
 * @see RelatedClass
 * @since 1.1.0
 */
```

## Questions or Issues?

- **Bug Reports**: Open an issue with:
  - Clear description
  - Steps to reproduce
  - Expected vs actual behavior
  - Environment details

- **Feature Requests**: Open an issue with:
  - Use case description
  - Proposed solution
  - Alternative approaches considered

- **Questions**: Open a discussion on GitHub

## License

By contributing, you agree that your contributions will be licensed under the same MIT License that covers the project.

## Code of Conduct

- Be respectful and inclusive
- Welcome newcomers
- Focus on constructive feedback
- Assume good intentions

Thank you for contributing to ChessAssistPlugin! 🎉

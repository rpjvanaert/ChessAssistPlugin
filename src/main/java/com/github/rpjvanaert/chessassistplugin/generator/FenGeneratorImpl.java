package com.github.rpjvanaert.chessassistplugin.generator;

import com.github.rpjvanaert.chessassistplugin.core.FenPosition;
import com.github.rpjvanaert.chessassistplugin.core.FenPositionImpl;

/**
 * Default implementation of FenGenerator.
 */
public class FenGeneratorImpl implements FenGenerator {
    
    private static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    private static final String EMPTY_BOARD = "8/8/8/8/8/8/8/8";
    
    @Override
    public FenPosition generateStartingPosition() {
        return new FenPositionImpl(
            STARTING_POSITION,
            "w",
            "KQkq",
            "-",
            0,
            1
        );
    }
    
    @Override
    public FenPosition generateEmptyBoard() {
        return new FenPositionImpl(
            EMPTY_BOARD,
            "w",
            "-",
            "-",
            0,
            1
        );
    }
    
    @Override
    public FenPosition generateCustomPosition(String piecePlacement, String activeColor,
                                             String castling, String enPassant,
                                             int halfmove, int fullmove) {
        return new FenPositionImpl(
            piecePlacement,
            activeColor,
            castling,
            enPassant,
            halfmove,
            fullmove
        );
    }
}

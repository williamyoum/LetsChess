package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;


public abstract class Piece {
	// tile compiles and has a single reference to Piece
	// what is a piece?
	// what can a piece do?
	// when can a piece not do what it can do?
	// every piece has a tile coordinate
	PieceType pieceType;
	boolean isFirstMove;
	protected final int piecePosition;
	protected final Alliance pieceAlliance; 	// piece will have a color
	// private final int cachedHashCode;
	Piece(final int piecePosition, 
		  final Alliance pieceAlliance,
		  final PieceType type,
		  final boolean isFirstMove){
		this.pieceType = type;
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.isFirstMove = isFirstMove;
//		this.cachedHashCode = computeHashCode();
	}
	
	public PieceType getPieceType() {
        return this.pieceType;
    }
    public Alliance getPieceAllegiance() {
        return this.pieceAlliance;
    }
    public int getPiecePosition() {
        return this.piecePosition;
    }
    public boolean isFirstMove() {
        return this.isFirstMove;
    }
    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }
    public abstract int locationBonus();

    public abstract Piece movePiece(Move move);

    public abstract Collection<Move> calculateLegalMoves(final Board board);


    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return this.piecePosition == otherPiece.piecePosition && this.pieceType == otherPiece.pieceType &&
               this.pieceAlliance == otherPiece.pieceAlliance && this.isFirstMove == otherPiece.isFirstMove;
    }
/*    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    private int computeHashCode() {
        int result = this.pieceType.hashCode();
        result = 31 * result + this.pieceAlliance.hashCode();
        result = 31 * result + this.piecePosition;
        result = 31 * result + (this.isFirstMove ? 1 : 0);
        return result;
    }*/
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	// most important method on Piece class:
	// responsible for finding the legal moves of a piece
	public abstract Collection<Move> findLegalMoves(final Board board);
	// we can return a set, or or unspecified collection of moves

}
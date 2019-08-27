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
	
	protected final int piecePosition;
	// piece will have a color
	protected final Alliance pieceAlliance;
	// takes position and color
	Piece(final int piecePosition, final Alliance pieceAlliance){
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
	}
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	// most important method on Piece class:
	// responsible for finding the legal moves of a piece
	public abstract Collection<Move> findLegalMoves(final Board board);
	// we can return a set, or or unspecified collection of moves

}








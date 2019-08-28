package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

	// isLegalMove method

	//	public static isValidMove(){
	//		return null;
	//	}

	// makeMove method

	// regular move or attacking move?

	// en passant? 
	// castling? 
	// promoting a pawn?

	public class MoveFactory {

		public static Move getNullMove() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	
	
	
	private Move(final Board board, final Piece movedPiece, final int destinationCoordinate){
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	
	public static final class JustMove extends Move{
		
		public JustMove(final Board board, final Piece movedPiece, final int destinationCoordinate){
			super(board, movedPiece, destinationCoordinate);
		}
	}
	public static final class AttackMove extends Move{	
		final Piece attackedPiece;
		public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece){
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		
		}
	
	}
	
}

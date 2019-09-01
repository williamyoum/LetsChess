package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.JustMove;
import com.google.common.collect.ImmutableList;

public class King extends Piece{
	
	private static final int[] ALL_MOVES = {8,-8, -1, 1, -9, -7, 7, 9}; // keep in mind, Queen is Rook.java + Bishop.java combined
	boolean isCastled;
	boolean isKingSideCastlePossible;
	boolean isQueenSideCastlePossible;
		// these used to be private... figure out why it works when public and not private...
	public King(final int piecePosition, 
			    final Alliance pieceAlliance,
				final boolean isKingSideCastlePossible,
				final boolean isQueenSideCastlePossible) {
		super(piecePosition, pieceAlliance, PieceType.KING, true);
		this.isCastled = false;
		this.isKingSideCastlePossible = isKingSideCastlePossible;
		this.isQueenSideCastlePossible = isQueenSideCastlePossible;
	}
	public King(int piecePosition, 
			    Alliance pieceAlliance,
				boolean isFirstMove,
				boolean isCastled,
				boolean isKingSideCastlePossible,
				boolean isQueenSideCastlePossible){
		super(piecePosition, pieceAlliance, PieceType.KING, isFirstMove);
		this.isCastled = isCastled;
		this.isKingSideCastlePossible = isKingSideCastlePossible;
		this.isQueenSideCastlePossible = isQueenSideCastlePossible;
	}
	public boolean isCastled() {
        return this.isCastled;
    }
    public boolean isKingSideCastlePossible() {
        return this.isKingSideCastlePossible;
    }
    public boolean isQueenSideCastlePossible() {
        return this.isQueenSideCastlePossible;
    }
	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		 for (final int currentCandidateOffset : ALL_MOVES) {
	            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
	                isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
	                continue;
	            }
	            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
	            if (BoardUtils.isValidTilePosition(candidateDestinationCoordinate)) {
	                final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
	                if (pieceAtDestination == null) {
	                    legalMoves.add(new JustMove(board, this, candidateDestinationCoordinate));
	                } else {
	                    final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAllegiance();
	                    if (this.pieceAlliance != pieceAtDestinationAllegiance) {
	                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate,
	                                pieceAtDestination));
	                    }
	                }
	            }
	        }
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public String toString() {
		return PieceType.KING.toString();
	}
/*	@Override
	public String toString() {
		return this.pieceType.toString();
	}
	*/
	@Override
	public int locationBonus() {
		return this.pieceAlliance.kingBonus(this.piecePosition);
	}
/*	@Override
	public King movePiece(Move move) {
		return new King(move.getDestinationCoordinate(), this.pieceAlliance,false, Move.isCastlingMove(), false, false);
	}*/
	
	@Override
	public boolean equals(final Object other) {
		if(this == other) {
			return false;
		}
		if(!(other instanceof King)) {
			return false;
		}
		if(!super.equals(other)) {
			return false;
		}
		
		final King king = (King) other;
		return isCastled == king.isCastled;
	}
	
	@Override
    public int hashCode() {
        return (31 * super.hashCode()) + (isCastled ? 1 : 0);
    }

	
	@Override
	public Collection<Move> findLegalMoves(Board board) {
		return null;
	}
	
	// edge cases
		private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {	
			return BoardUtils.FIRST_COLUMN[currentPosition] 
					&& (candidateOffset == -1) 
					|| (candidateOffset == -9) 
					|| (candidateOffset == 7);
		}
		private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
			return BoardUtils.EIGHTH_COLUMN[currentPosition] 
					&& (candidateOffset == 1) 
					|| (candidateOffset == 9) 
					|| (candidateOffset == -7);
		}
		@Override
		public Piece movePiece(Move move) {
			return null;
		}

}

package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

public class Rook extends Piece{
	Rook(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	private static final int[] MOVE_VECTOR_COORDINATES = {8,-8, -1, 1};

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset: MOVE_VECTOR_COORDINATES) {
			int candidateDestinationCoordinate = this.piecePosition;
		}
		while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
			if(isFirstColumnExclusion())
		}
		
		
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
		
		// on first column
		// rook can go up, right, down... not left
		
	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
		
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1);
		// on first column
		// rook can go up, left, down... not right
		
	}
}

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

public class Rook extends Piece{
	
	
	public Rook(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance, PieceType.ROOK, true);
		// TODO Auto-generated constructor stub
	}
	public Rook(int piecePosition, Alliance pieceAlliance, boolean isFirstMove) {
		super(piecePosition, pieceAlliance, PieceType.ROOK, isFirstMove);
		// TODO Auto-generated constructor stub
	}
	
	
	
	private static final int[] ALL_MOVES = {8,-8, -1, 1};

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset: ALL_MOVES) {
			int focusPosition = this.piecePosition;
		
			while(BoardUtils.isValidTileCoordinate(focusPosition)) {
				if(isFirstColumnExclusion(focusPosition, candidateCoordinateOffset)
						|| isEighthColumnExclusion(focusPosition, candidateCoordinateOffset)) {
					break;
				}
			focusPosition += candidateCoordinateOffset; // relook at other moves
			}
			if(BoardUtils.isValidTileCoordinate(focusPosition)) {
				final Tile candidateDestinationTile = board.getTile(focusPosition);
				if(!candidateDestinationTile.isOccupied()) {
					legalMoves.add(new JustMove(board, this, focusPosition));
					// get an unoccupied tile from the board and add it to the legal move list.
				}
				else {
					// attacking legal move
					// get alliance of piece already on the tile
					// if the alliance is not equal to the currKnight, this is an attacking move
					final Piece pieceOnTile = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceOnTile.getPieceAlliance();
				
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new AttackMove(board, this, focusPosition, pieceOnTile));
					}
					break;
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}
		
	// edge cases
	
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

	@Override
	public int locationBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Piece movePiece(Move move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
}

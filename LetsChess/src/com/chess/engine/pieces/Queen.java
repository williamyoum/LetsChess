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

public class Queen extends Piece{
	public Queen(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance, PieceType.QUEEN, true);
		// TODO Auto-generated constructor stub
	}
	Queen(final int piecePosition, final Alliance pieceAlliance, boolean isFirstMove) {
		super(piecePosition, pieceAlliance, PieceType.QUEEN, isFirstMove);
		// TODO Auto-generated constructor stub
	}
	private static final int[] ALL_MOVES = {8,-8, -1, 1, -9, -7, 7, 9}; // keep in mind, Queen is Rook.java + Bishop.java combined

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset: ALL_MOVES) {
			int piecePosition = this.piecePosition;
		
			while(BoardUtils.isValidTilePosition(piecePosition)) {
				if(isFirstColumnExclusion(piecePosition, candidateCoordinateOffset)
						|| isEighthColumnExclusion(piecePosition, candidateCoordinateOffset)) {
					break;
				}
			piecePosition += candidateCoordinateOffset; // re-look at other moves
			}
			if(BoardUtils.isValidTilePosition(piecePosition)) {
				final Tile candidateDestinationTile = board.getTile(piecePosition);
				if(!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new JustMove(board, this, piecePosition));
					// get an unoccupied tile from the board and add it to the legal move list.
				}
				else {
					// attacking legal move
					// get alliance of piece already on the tile
					// if the alliance is not equal to the currKnight, this is an attacking move
					final Piece pieceOnTile = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceOnTile.getPieceAlliance();				
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new AttackMove(board, this, piecePosition, pieceOnTile));
					}
					break;
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public String toString() {
		return PieceType.QUEEN.toString();
	}
	// edge cases
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {	
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1) || (candidateOffset == -9); 
		// by nature of the queen, it can hit the +7 tile

	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1) || (candidateOffset == 9);
		// by nature of the queen, it can hit the -7 tile
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
	public Collection<Move> findLegalMoves(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.JustMove;

public class Bishop extends Piece {
														// these are the possible moves the bishop can make. 
														// they are relative to the current position.
	private static final int[] ALL_MOVES = {-9, -7, 7, 9}; 
	Bishop(final int piecePosition, final Alliance pieceAlliance) { // this method takes the bishop's position, color.
		super(piecePosition, pieceAlliance, PieceType.BISHOP, true);
	}
	
	Bishop(final int piecePosition, final Alliance pieceAlliance, boolean isFirstMove) { // this method takes the bishop's position, color.
		super(piecePosition, pieceAlliance, PieceType.BISHOP, isFirstMove);
	}
	@Override
	public Collection<Move> findLegalMoves(Board board) { // this is a collection that uses findLegalMoves to calculate the legal moves
		List<Move> legalMoves = new ArrayList<>();
	// this is the loop for the whole list of possible legal moves
		for(final int candidateCoordinateOffset: ALL_MOVES) { // loop through each of the possible moves and validate
			int focusPosition = this.piecePosition; // focusPosition holds the currPosition
			while(BoardUtils.isValidTileCoordinate(focusPosition)) { //			
				
				if(isFirstColumnExclusion(focusPosition, candidateCoordinateOffset) 
				|| isEighthColumnExclusion(focusPosition, candidateCoordinateOffset)) {
					break;
				}
				focusPosition += candidateCoordinateOffset;  // make the position + offset become legal move
				// this is now one of the legal moves
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
					// if(this.pieceAlliance == pieceAlliance){
					// 		move on, because this is not a legal move. you cannot go where your team is.
					// }
				}		
			}	
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	//edge cases
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
		
	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
		
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 9 || candidateOffset == -7);
		
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

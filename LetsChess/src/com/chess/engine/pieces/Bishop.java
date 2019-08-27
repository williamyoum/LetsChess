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
	private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9}; 
	Bishop(int piecePosition, Alliance pieceAlliance) { // this method takes the bishop's position, color.
		super(piecePosition, pieceAlliance);
	}
	@Override
	public Collection<Move> findLegalMoves(Board board) { // this is a collection that uses findLegalMoves to calculate the legal moves
		List<Move> legalMoves = new ArrayList<>();
	// this is the loop for the whole list of possible legal moves
		for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES) { // loop through each of the possible moves and validate
			int candidateDestinationCoordinate = this.piecePosition; // candidateDestinationCoordinate holds the currPosition
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { //			
				
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) 
				|| isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
					break;
				}
				candidateDestinationCoordinate += candidateCoordinateOffset;  // make the position + offset become legal move
				// this is now one of the legal moves
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isOccupied()) {
						legalMoves.add(new JustMove(board, this, candidateDestinationCoordinate));
						// get an unoccupied tile from the board and add it to the legal move list.
					}
					else {
						// attacking legal move
						// get alliance of piece already on the tile
						// if the alliance is not equal to the currKnight, this is an attacking move
						final Piece pieceOnTile = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceOnTile.getPieceAlliance();
					
						if(this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnTile));
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
}

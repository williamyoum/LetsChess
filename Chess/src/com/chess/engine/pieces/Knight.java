package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	//private int isValidTileCoordinate;

	
	Knight(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Move> findLegalMoves(Board board) {
		// TODO Auto-generated method stub
		
		// loop through all of the candidate options
		int candidateDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			// if not occupied, legal move.
			// if occupied, then..
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {	
				if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) 
						|| isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) 
						|| isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) 
						|| isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
					continue; // continue through the loop
					
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				if(!candidateDestinationTile.isOccupied()) {
					legalMoves.add(new Move());
				}
				else {
					// attacking legal move
					// get alliance of piece already on the tile
					// if the alliance is not equal to the currKnight, this is an attacking move
					// 
					final Piece pieceOnTile = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceOnTile.getPieceAlliance();
				
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new Move());
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
//	
//	// this you can create in another class
//	private static boolean isValidTileCoordinate(int coordinate) {
//		return coordinate >= 0 && coordinate > 64;
//	}

	private static boolean isFirstColumnExclusion(final int currPosition, final int candidateOffset) {
		// introduce array of booleans in BoardUtils
		//
		return BoardUtils.FIRST_COLUMN[currPosition] && ((candidateOffset == -17) || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
		
	}
	private static boolean isSecondColumnExclusion(final int currPosition, final int candidateOffset) {
		// introduce array of booleans in BoardUtils
		//
		return BoardUtils.SECOND_COLUMN[currPosition] && ((candidateOffset == -10) || candidateOffset == 6);
		
	}
	private static boolean isSeventhColumnExclusion(final int currPosition, final int candidateOffset) {
		// introduce array of booleans in BoardUtils
		//
		return BoardUtils.SEVENTH_COLUMN[currPosition] && ((candidateOffset == 10) || candidateOffset == -6);
		
	}
	private static boolean isEighthColumnExclusion(final int currPosition, final int candidateOffset) {
		// introduce array of booleans in BoardUtils
		//
		return BoardUtils.EIGHTH_COLUMN[currPosition] && ((candidateOffset == 17) || candidateOffset == 10 || candidateOffset == -6 || candidateOffset == -15);
		
	}
	// how to calculate legal moves for a knight
	// ex: currPos = d4 tile
	// tiles num: 35
	// 35 - 6... legal move
	// 
	
	
}

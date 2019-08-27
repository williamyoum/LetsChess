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

	public class Pawn extends Piece{
		Pawn(final int piecePosition, final Alliance pieceAlliance) {
			super(piecePosition, pieceAlliance);
			// TODO Auto-generated constructor stub
		}
		private static final int[] ALL_MOVES = {-8}; // is -8 up or down? im assuming the og set the A8 tile as the 0th tile...

		
		
		// calculate legal moves   ============================================
		@Override
		public Collection<Move> findLegalMoves(Board board) {
			List<Move> legalMoves = new ArrayList<>();
			for(final int candidateCoordinateOffset: ALL_MOVES) {
				int focusPosition = this.piecePosition;

				// the pawn does not have edge cases... 
				// don't forget the 
/*
				while(BoardUtils.isValidTileCoordinate(focusPosition)) {
					if(isFirstColumnExclusion(focusPosition, candidateCoordinateOffset)
							|| isEighthColumnExclusion(focusPosition, candidateCoordinateOffset)) {
						break;
					}
 */
				focusPosition += candidateCoordinateOffset; // relook at other moves
			
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
						int piecePosition = pieceOnTile.piecePosition;
						// you already have a method for getTile. use it.
						if((this.pieceAlliance != pieceAlliance) && ((this.piecePosition == piecePosition + 9) || (this.piecePosition == piecePosition + 7))) {
							legalMoves.add(new AttackMove(board, this, focusPosition, pieceOnTile));
						}
						break;
					}
				}
//				}
			}
			return ImmutableList.copyOf(legalMoves);
	}

		// implement pawn promotion
		
}


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

	public final class Pawn extends Piece{
		
		public Pawn(int piecePosition, Alliance pieceAlliance) {
			super(piecePosition, pieceAlliance, PieceType.PAWN, true);
			// TODO Auto-generated constructor stub
		}
		
		public Pawn(int piecePosition, Alliance pieceAlliance, boolean isFirstMove) {
			super(piecePosition, pieceAlliance, PieceType.PAWN, isFirstMove);
		}
		private static final int[] ALL_MOVES = {8, 16, 7, 9 }; // is -8 up or down? im assuming the og set the A8 tile as the 0th tile... 
															  // nvm 0th tile is A1.
		// calculate legal moves   ============================================
		@Override
		public Collection<Move> calculateLegalMoves(Board board) {
			List<Move> legalMoves = new ArrayList<>();
			for(final int candidateCoordinateOffset: ALL_MOVES) {
				int piecePosition = this.piecePosition;
				// the pawn does not have edge cases... 
				// don't forget the 
/*
				while(BoardUtils.isValidTileCoordinate(piecePosition)) {
					if(isFirstColumnExclusion(piecePosition, candidateCoordinateOffset)
							|| isEighthColumnExclusion(piecePosition, candidateCoordinateOffset)) {
						break;
					}
 */
				piecePosition += candidateCoordinateOffset; // relook at other moves

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
						piecePosition = pieceOnTile.piecePosition;
						// you already have a method for getTile. use it.
						if((this.pieceAlliance != pieceAlliance) && ((this.piecePosition == piecePosition + 9) || (this.piecePosition == piecePosition + 7))) {
							legalMoves.add(new AttackMove(board, this, piecePosition, pieceOnTile));
						}
						break;
					}
				}
//				}
			}
			return ImmutableList.copyOf(legalMoves);
	}
		@Override
		public String toString() {
			return PieceType.PAWN.toString();
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

		// implement pawn promotion
}


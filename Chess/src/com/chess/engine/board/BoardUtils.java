package com.chess.engine.board;

public class BoardUtils {

	// elaborate...
	
	public static final boolean[] FIRST_COLUMN = null ;
	public static final boolean[] SECOND_COLUMN = null ;
	public static final boolean[] SEVENTH_COLUMN = null ;
	public static final boolean[] EIGHTH_COLUMN = null ;
	
	// check if the move is in bounds or out of bounds
	private BoardUtils() {
		throw new RuntimeException("You Cannot Instantiate this.");
	}
	
	
	public static boolean isValidTileCoordinate(int coordinate) {
		return coordinate >= 0 && coordinate < 64;
		
		
	}

}

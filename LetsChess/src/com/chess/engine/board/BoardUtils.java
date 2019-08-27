package com.chess.engine.board;

public class BoardUtils {

	// elaborate...
	
	public static final boolean[] FIRST_COLUMN = initColumn(0) ;
	public static final boolean[] SECOND_COLUMN = initColumn(1) ;
	public static final boolean[] SEVENTH_COLUMN = initColumn(5) ;
	public static final boolean[] EIGHTH_COLUMN =initColumn(7) ;
	
	public static final int NUMTILES = 64;
	public static final int NUMTILES_PER_ROW = 8;
	private static boolean[] initColumn(int columnNumber) {
	
		final boolean column[] = new boolean[NUMTILES];
		do{
			column[columnNumber] = true;
			columnNumber += NUMTILES_PER_ROW;	
		}while (columnNumber < NUMTILES);
		return column;
	}
	// check if the move is in bounds or out of bounds
	private BoardUtils() {
		throw new RuntimeException("You Cannot Instantiate this.");
	}
	public static boolean isValidTileCoordinate(final int coordinate) {
		return coordinate >= 0 && coordinate < NUMTILES;
	}
}

package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

//com.chess.engine.board;
//com.chess.engine.pieces;

public abstract class Tile {
	
	// abstract
		// cannot instantiate this class... "New Tile"
		// but can instantiate concrete subclasses
	// this class consists of checking for: 
		// occupied tile
		// empty tile
	
	// there are 8x8 chess tiles
	// this means there should be 64 chess tiles
	
	// create abstraction for single chess tile
	// 0-63 tiles
	
	
	// this is mutable
	// this isn't defined 
	// instance of tileCoordinate and reference that field on it and mutate it
	// reference this for clients that use this API
	// protected
	// and final so that once this is set inside constructor, 
	// it can only be set once.
	protected final int tileCoordinate;
	
	private static final Map<Integer, emptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private static Map<Integer, emptyTile> createAllPossibleEmptyTiles(){
		
		final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();
		for(int i = 0; i < BoardUtils.NUMTILES; i++) {
			emptyTileMap.put(i, new emptyTile(i));
		}
		// create emptyTiles first, once
		return ImmutableMap.copyOf(emptyTileMap);
			// Guava is handy
		// ImmutableMap 
		// we want an immutable map
		// this is a container
		// if we want this, 
		// after constructing empty tile map, we don't want anyone to change it
		// Google in Guava jar lib will do that
	}
	
	// create a new occupiedTile only way to create a new tile
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
			return piece != null ? new occupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}
	
	
	// get a tile coordinate
	private Tile(final int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
	}
	
	// useful methods
	// check if tile is occupied
	
	public abstract boolean isOccupied();
	
	public abstract Piece getPiece();
	// if occupied, return non-null char 
	//if empty return null;
	
	// extends expands the Tile superclass
	// this also instantiates new tiles
	public static final class emptyTile extends Tile{
		// make constructor final too
		emptyTile(final int coordinate){
			super(coordinate);
		}
	
		
		@Override
		public boolean isOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	// subclasses...
	// declare occupiedTile in same file
	// this is so that there is a coherence of tile activity
	public static final class occupiedTile extends Tile{
		private final Piece pieceOnTile; 
		// this is mutable too
		// class private
		// no way to ref this var without calling getPiece
		// constants and caching... 
			// we can create all of the emptyTiles that are valid 
			// whenever we want to retreive one, instead of creating a new one
			// we can look it up in the cache
		occupiedTile(int tileCoordinate, final Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
	
		@Override
		public boolean isOccupied() {
			return true;
		}
	
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}
}

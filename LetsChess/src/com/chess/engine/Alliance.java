package com.chess.engine;

import com.chess.engine.board.BoardUtils;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;

public enum Alliance {

	WHITE(){
	// figure this out
		public boolean isWhite() {
			return true;
		}
		
		public boolean isBlack() {
			return false;
		}
	    
	    public Player choosePlayerByAlliance(final WhitePlayer whitePlayer,
	                                         final BlackPlayer blackPlayer) {
	    	
	        return whitePlayer;
	    }
	 
		public int kingBonus(final int position){
			return 0;
		}
	},
	BLACK(){
		public boolean isWhite() {
			return false;
		}
		
		public boolean isBlack() {
			return true;
		}
	    
	    public Player choosePlayerByAlliance(final WhitePlayer whitePlayer,
	                                         final BlackPlayer blackPlayer) {
	    	
	        return blackPlayer ;
	    }
	 
		public int kingBonus(final int position){
			return 0;
		}
	};
    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract int kingBonus(int position);
    public abstract Player choosePlayerByAlliance(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);

}

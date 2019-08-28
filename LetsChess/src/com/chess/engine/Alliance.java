package com.chess.engine;

import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;

// we use enum because without it it is not typesafe
// only two constant instances: white and black
// 

public enum Alliance {
	WHITE, BLACK;

	public Player choosePlayerByAlliance(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
		// TODO Auto-generated method stub
		return null;
	}
}

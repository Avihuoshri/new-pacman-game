package Threads;

import GUI.pacmanBoard;
import Game_figures.Game;

public class ThreadAutoPlayer extends Thread
{

	pacmanBoard pb ;
	Game game  ;
	
	public ThreadAutoPlayer(pacmanBoard board)
	{
		pb = board ;
		game = board.getGame() ;
	}
	
	public void run()
	{
		while(game.getGameEnd() == false)
		{
			
		}
	}
}

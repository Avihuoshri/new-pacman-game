package Threads;

import GUI.pacmanBoard;
import Game_figures.Game;

public class TimeThread extends Thread 
{
	private int DECREASE_SECONED = -1 ;
	private pacmanBoard pucamanBoard ;
	private Game game ;
	public TimeThread(pacmanBoard pb)
	{
		pucamanBoard = pb ;
		game = pb.getGame() ;
	}
	
	public void run()
	{
		decreamentTime() ;
	}
	
	private synchronized void decreamentTime()
	{
		while(game.getGameTime() > 0 && game.getGameEnd() == false )
		{
			game.setgameTime(DECREASE_SECONED);
			System.out.println(game.getgameTime());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(game.toString());
	}
	public static void main(String[] args)
	{
		int i = 100 ;
		while(i > 0)
		{
			i-- ;
			System.out.println(i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

package Threads;

import GUI.pacmanBoard;
import Game_figures.Game;



public class Move extends Thread {
	pacmanBoard pacBoard;
	Game game;

	/**
	 * constract the move 
	 * @param packBoard
	 */
	
	public Move(pacmanBoard packBoard) {
		this.pacBoard=packBoard;
		this.game=packBoard.getGame() ;
	}

	/**
	 * run the thread
	 */

	public void run(){
		synchronized(pacBoard) {
			while(pacBoard.getEX4play().isRuning()) {
				pacBoard.nextMove();
				System.out.println("game is running");
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}	


	}
}

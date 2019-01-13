package Threads;

import GUI.pacmanBoard;

public class paintGame extends Thread
{
	pacmanBoard myFrame ;

	/**
	 * constract the paint game
	 * @param pb
	 */

	public paintGame(pacmanBoard pb)
	{
		myFrame = pb ;
	}
	
	/**
	 * run the thread
	 */

	public   void run()
	{
		try {
			sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myFrame.repaint();

	}
}

package Threads;

import GUI.pacmanBoard;

public class paintGame extends Thread
{
	pacmanBoard myFrame ;
	
	public paintGame(pacmanBoard pb)
	{
		myFrame = pb ;
	}
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

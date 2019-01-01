package Threads;

import GUI.pacmanBoard;

public class paintGame extends Thread
{
	pacmanBoard myFrame ;
	
	public paintGame(pacmanBoard pb)
	{
		myFrame = pb ;
	}
	public void run()
	{
		myFrame.repaint();
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

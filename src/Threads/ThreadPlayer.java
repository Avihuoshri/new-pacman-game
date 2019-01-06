package Threads;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GUI.Eatting_effect;
import GUI.pacmanBoard;
import Game_figures.Box;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Player;
import Geom.Point3D;

public class ThreadPlayer extends Thread implements KeyListener
{
	final int DECREASE_POINT = -1 ;
	pacmanBoard myFrame ;
	int mapW , mapH ;
	Game game ;
	Fruit fruit ;
	Point3D direction ;
	Eatting_effect effect ;

	public ThreadPlayer(pacmanBoard pb  )
	{
		Init(pb) ;
	}
	public void Init(pacmanBoard pb )
	{
		myFrame = pb ;
		game = pb.getGame() ;
		direction = pb.getGame().getPlayer().getDirection() ;
	}

	public void run()
	{
		Game game = myFrame.getGame() ;
		Play(game) ;
	}


	public void  Play(Game game  )
	{
		boolean touchedWall = false ;
		double distance = 0 ;
		int newY ;
		int newX = direction.ix() ;
		int oldX = game.getPlayer().getPlayerLocation().ix() ;
		int oldY = game.getPlayer().getPlayerLocation().iy() ;
		boolean notInBox = true ;
		Player player = game.getPlayer() ;

		int i = 0 ;

		while(i<10 && touchedWall == false )
		{
			for (Box box : game.boxSet) 
			{
				//				System.out.println("box lower point X = " + box.getLowerPoint().ix() + "     box lower point Y = "+box.getLowerPoint().iy());
				if(oldX >= box.getLowerPoint().ix() && oldX <= box.getUpperPoint().ix() )
				{
					if(oldY <= box.getLowerPoint().iy() && oldY>= box.getUpperPoint().iy()) 
					{
						notInBox = false ;
						game.setScore(DECREASE_POINT);
						i++ ;
						touchedWall = true ;
						interrupt();

					}
				}
			}


			if(game.getPlayer().getPlayerLocation().ix() < direction.ix())
			{
				newX = player.getPlayerLocation().ix() +1 ;
				newY = whereIsY(player , direction);
			}

			else if(game.getPlayer().getPlayerLocation().ix() > direction.ix())
			{
				newX = player.getPlayerLocation().ix() - 1 ;
				newY = whereIsY(player , direction);
			}

			else
				newY = whereIsY(player , direction);

			for (Fruit fruit : game.fruitSet) 
			{
				distance = player.distanceToFruit(player, fruit);
				if(distance < 6)
				{
					game.setScore(1);
					game.fruitSet.remove(fruit)  ;
				}
			}
			Point3D newPoint = new Point3D(newX , newY) ;
			player.setPlayerLocation(newPoint);;

			if(distance<=1)
			{
				game.setScore(DECREASE_POINT);
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			paintGame pb = new paintGame(myFrame);
			pb.start();
			try {
				sleep(25);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//			System.out.println("PACMAN ID : " +pacman.getId()+ "  distance------>"  + distance);
			//		}
			i++ ;
		}
	}




	private int whereIsY(Player Player , Point3D direction)
	{
		int y ;
		if(Player.getPlayerLocation().iy() < direction.iy())
		{   y = Player.getPlayerLocation().iy() +1 ;    }

		else if(Player.getPlayerLocation().iy() > direction.iy())
		{   y = Player.getPlayerLocation().iy() -1 ;    }

		else
		{  y=  Player.getPlayerLocation().iy()  ;         }

		return y ;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.equals(obj))
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}

package Threads;

import GUI.Eatting_effect;
import GUI.pacmanBoard;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Ghost;
import Game_figures.Packman;
import Game_figures.Player;
import Geom.Point3D;

public class ThreadGhost extends Thread
{	
	final int DECREASE_POINT = -1 ;
	pacmanBoard myFrame ;
	int mapW , mapH ;
	Ghost ghost ;
	Game game ;
	Eatting_effect effect ;

	public ThreadGhost(pacmanBoard myFrame , Ghost ghost )
	{
		this.myFrame = myFrame ;
		this.ghost = ghost ;
		
		game = myFrame.getGame() ;

		effect = new Eatting_effect() ;

	}

	public void run()
	{
		Game game = myFrame.getGame() ;
		Play(game) ;
	}


	public void  Play(Game game  )
	{
		boolean fruitEaten = false ;
		double distance = 0 ;
		int newY ;
		int newX = ghost.getG_point().ix() ;
		Player player = game.getPlayer() ;
		while(game.getGameEnd() == false)
		{

			if(game.getPlayer().getPlayerLocation().ix() > ghost.getG_point().ix())
			{
				newX = ghost.getG_point().ix() +1 ;
				newY = whereIsY(player , ghost);
			}

			else if(game.getPlayer().getPlayerLocation().ix() < ghost.getG_point().ix())
			{
				newX = ghost.getG_point().ix() - 1 ;
				newY = whereIsY(player , ghost);
			}

			else
				newY = whereIsY(player , ghost); 

			distance = ghost.distanceToPlayer(player, ghost);
			Point3D newPoint = new Point3D(newX , newY) ;
			ghost.setG_point(newPoint);
			
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
					sleep(50);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//			System.out.println("PACMAN ID : " +pacman.getId()+ "  distance------>"  + distance);
				//		}
			}
		}		

	


	private int whereIsY(Player Player , Ghost ghost)
	{
		int y ;
		if(Player.getPlayerLocation().iy() < ghost.getG_point().iy())
		{   y = ghost.getG_point().iy() -1 ;    }

		else if(Player.getPlayerLocation().iy() > ghost.getG_point().iy())
		{   y = ghost.getG_point().iy() +1 ;    }

		else
		{  y=  ghost.getG_point().iy()  ;         }

		return y ;
	}









}

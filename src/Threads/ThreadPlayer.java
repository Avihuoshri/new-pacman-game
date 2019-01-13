package Threads;


import GUI.Eatting_effect;
import GUI.pacmanBoard;
import Game_figures.Box;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;
import Game_figures.Player;
import Geom.Point3D;

public class ThreadPlayer extends Thread 
{
	final int DECREASE_POINT = -1 ;
	final int INCREASE_POINT = 1  ;
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
		move(game) ;
	}


	private void  move(Game game)
	{
		boolean touchedBox = false ;
		double fruitDistance = 0  ;
		double pacmanDistance = 0 ;
		int newY ;
		int newX = direction.ix() ;
		int oldX = game.getPlayer().getPlayerLocation().ix() ;
		int oldY = game.getPlayer().getPlayerLocation().iy() ;
		Player player = game.getPlayer() ;

		int i = 0 ;

		while(i<10 && game.getGameEnd() == false && game.getgameTime() > 0 )
		{
			
//			if(touchedBox == false)
//			{
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
				
				fruitDistance = player.distanceToFruit(player, fruit.getFruitLocation());
				if(fruitDistance < 6)
				{
					game.setScore(INCREASE_POINT);
					game.setPlayerEatenFruit(INCREASE_POINT);
					game.fruitSet.remove(fruit)  ;
					for (Packman pacman : game.pacmanSet) 
					{
						if(pacman.getP_Path().getFruitsPath().contains(fruit))
						{
							pacman.getP_Path().getFruitsPath().remove(fruit) ;
						}
					}
				}
				
				for (Packman pacman : game.pacmanSet) 
				{
					 
					pacmanDistance = player.distanceToFruit(player, pacman.getP_Location());
					if(pacmanDistance < 30)
					{
						game.setScore(INCREASE_POINT);
						game.pacmanWasEaten(pacman);
						game.pacmanSet.remove(pacman)  ;
					}
				}
				
				
			}
		
			Point3D newPoint = new Point3D(newX , newY) ;

//			if(fruitDistance<=1)
//			{
//				game.setScore(DECREASE_POINT);
//				try {
//					sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			for (Box box : game.boxSet) 
			{
				//				System.out.println("box lower point X = " + box.getLowerPoint().ix() + "     box lower point Y = "+box.getLowerPoint().iy());
				if(newPoint.ix() >= box.getLowerPoint().ix() && newPoint.ix() <= box.getUpperPoint().ix() )
				{
					if(newPoint.iy() <= box.getLowerPoint().iy() && newPoint.iy() >= box.getUpperPoint().iy()) 
					{
						game.setScore(DECREASE_POINT);
						game.setPlayerTouchedBox(INCREASE_POINT);
						System.out.println("SCORE : " + game.getScore());
						i++ ;
						touchedBox = true ;
						newPoint = new Point3D(oldX , oldY) ;

					}
				}
			}
			player.setPlayerLocation(newPoint);

			paintGame pb = new paintGame(myFrame);
			pb.start();
			try {
				sleep(25);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//			System.out.println("PACMAN ID : " +pacman.getId()+ "  distance------>"  + distance);
			//		}
			i++ ;
		}
		
//		if(game.getGameEnd() == true)
			

//			else
//			{
//				System.out.println("x");
//			}
		}
//	}




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

	
	

}

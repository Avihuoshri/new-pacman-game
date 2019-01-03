package Threads;

import java.awt.Point;
import java.util.ArrayList;

import Algorithms.ShortestPathAlgo;
import GUI.Eatting_effect;
import GUI.pacmanBoard;
import GUI.pacmanBoard;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Ghost;
import Game_figures.Packman;
import Geom.Point3D;

public class ThreadPacman extends Thread {
	pacmanBoard myFrame ;
	int mapW , mapH ;
	Packman pacman ;
	Game game ;
	Eatting_effect effect ;
					
	public ThreadPacman(pacmanBoard myFrame , int mapwidth , int mapHeight , Packman pacman )
	{
		this.myFrame = myFrame ;
		this.pacman = pacman ;
		mapW = mapwidth ;
		mapH = mapHeight ;
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
		while(pacman.getP_Path().getFruitsPath().size() > 0)
		{

			Fruit fruit = pacman.getP_Path().getFruitsPath().get(0);
//			System.out.println("in first loop");
			fruitEaten = false ;
			int y ;
			int newX = pacman.getP_Location().ix() ;
//			Point3D tempPacmanPoint = new Point3D(pacman.getP_Location());

			while( fruitEaten == false)
			{
				if(fruit.getFruitLocation().ix() > pacman.getP_Location().ix())
				{
					newX = pacman.getP_Location().ix() +1 ; 
					y = whereIsY(pacman, fruit);
				}
				
				else	if(fruit.getFruitLocation().ix() < pacman.getP_Location().ix())
				{
					newX = pacman.getP_Location().ix() -1 ; 
				
					y = whereIsY(pacman, fruit);
				}
				
				else
					y = whereIsY(pacman, fruit);

				distance = pacman.point_A_toPoint_B(fruit, myFrame.getWidth(), myFrame.getHeight(), game);

				Point3D newPoint = new Point3D(newX , y) ;
				pacman.setPixelLocation(newPoint);
				
				if(distance < 1 )
				{
					fruitEaten = true ;
					game.fruitSet.remove(fruit) ;
					pacman.getP_Path().getFruitsPath().remove(0) ;			
					myFrame.setPaintFruit();
					myFrame.getEffect().setActivate();
					myFrame.getEffect().setEpoint(fruit);
					
//					ShortestPathAlgo spa = new ShortestPathAlgo(game);
				}
				paintGame pb = new paintGame(myFrame);
				pb.start();
				try {
					sleep(50);
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("PACMAN ID : " +pacman.getId()+ "  distance------>"  + distance);
//			}
		}
		}		
		
		}
		
		
		private int linearEquition(Point3D packman , Fruit fruit , int pacman_X)
		{
			int pacman_x , pacman_y , fruit_x , fruit_y , ans_Y ;
			double Incline ;
			pacman_x =pacman.getP_Location().ix()  ;
			pacman_y = pacman.getP_Location().iy() ;
			fruit_x = fruit.getFruitLocation().ix();
			fruit_y = fruit.getFruitLocation().iy();
			
			Incline = (pacman_y - fruit_y ) / (pacman_x - fruit_x) ;
			
			ans_Y = (int) Incline*(pacman_X - fruit_x) + fruit_y ;
			return ans_Y  ;			
		}
		
		private int whereIsY(Packman pacman , Fruit fruit)
		{
			int y ;
			if(fruit.getFruitLocation().iy() < pacman.getP_Location().iy())
			{   y = pacman.getP_Location().iy() -1 ;    }
			
		    else if(fruit.getFruitLocation().iy() > pacman.getP_Location().iy())
			{   y = pacman.getP_Location().iy() +1 ;    }
			
		    else
			 {  y= pacman.getP_Location().iy()  ;         }
			
			return y ;
		}
		
		
		
		
		
		
		
		
	

}

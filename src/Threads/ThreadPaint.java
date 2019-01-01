package Threads;

import java.util.ArrayList;

import javafx.*;
import Algorithms.ShortestPathAlgo;
import GUI.pacmanBoard;
import GUI.pacmanBoard;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;
import Geom.Point3D;

public class ThreadPaint extends Thread {
	pacmanBoard myFrame ;
	int mapW , mapH ;
	Packman pacman ;
	Game game ;
	public ThreadPaint(pacmanBoard myFrame , int mapwidth , int mapHeight , Packman pacman)
	{
		this.myFrame = myFrame ;
		this.pacman = pacman ;
		mapW = mapwidth ;
		mapH = mapHeight ;
		game = myFrame.getGame() ;
		
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
		for (Fruit fruit : pacman.getP_Path().getFruitsPath()) 
		{
			System.out.println("in first loop");
			fruitEaten = false ;
			int y ;
			int newX ;
			y = linearEquition(pacman, fruit) ;

			while( fruitEaten == false)
			{
				System.out.println("in seconed loop");
				distance = pacman.point_A_toPoint_B(fruit, myFrame.getWidth(), myFrame.getHeight(), game);
				if(fruit.getFruitLocation().ix() > pacman.getP_Location().ix())
				newX = pacman.getP_Location().ix() + 5 ; 
				else
					newX = pacman.getP_Location().ix() - 5 ; 

				Point3D newPoint = new Point3D(newX , y) ;
				pacman.setPixelLocation(newPoint);
				if(distance < 1 )
				{
					fruitEaten = true ;
				}
				
				try {
					myFrame.repaint();

					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("PACMAN ID : " +pacman.getId()+ "  distance------>"  + distance);
//				fruitEaten = true ;
			}
		}		
		
//		
//		int sizeOfFruit = pacman.getP_Path().getFruitsPath().size() ;
//		for(int i = 0 ; i< 4; i++)
//		{
//			Point3D pacmanPoint = new Point3D(pacman.getP_Location()) ;
//			Point3D point = new Point3D(pacmanPoint.ix()+1 ,pacmanPoint.iy());
//			pacman.setPixelLocation(point);
//			myFrame.repaint();
////			pacman.getP_Path().getFruitsPath().remove(0) ;
//			try {
//				sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		
		public int linearEquition(Packman packman , Fruit fruit)
		{
			int pacman_x , pacman_y , fruit_x , fruit_y , ans_Y ;
			double Incline ;
			pacman_x =pacman.getP_Location().ix()  ;
			pacman_y = pacman.getP_Location().iy() ;
			fruit_x = fruit.getFruitLocation().ix();
			fruit_y = fruit.getFruitLocation().iy();
			
			Incline = (pacman_y - fruit_y ) / (pacman_x - fruit_x) ;
			
			ans_Y = (int) Incline*(pacman_x+1 - fruit_x) + fruit_y ;
			return ans_Y ;
			
			
		}
		
		
		
		
		
		
		
		
	

}

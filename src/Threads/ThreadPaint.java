package Threads;

import java.util.ArrayList;

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
		for(int i = 0 ; i<400 ; i++)
		{
			Point3D pacmanPoint = new Point3D(pacman.getP_Location()) ;
			Point3D point = new Point3D(pacmanPoint.ix()+1 ,pacmanPoint.iy());
			pacman.setPixelLocation(point);
			myFrame.repaint();
			try {
				sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	}
//
//		ShortestPathAlgo spa = new ShortestPathAlgo(game  ); /*לכל פקמן יש פרי שהוא הולך אליו*/
//		double distanceToFruit ;
//		int fruitId ;
//		Thread thread = new Thread() ;
//		while(game.fruitSet.size() > 0)
//		{
//			 spa = new ShortestPathAlgo(game  ); /*לכל פקמן יש פרי שהוא הולך אליו*/
//		for (Packman pacman : game.pacmanSet)
//		{
////			pacman.start();
//		}	
//		myFrame.repaint();
//		}
//		
//		
//		
//		int counter = 0 ; /*לא לשכוח למחוק*/
//		 /*************************************לא למחוק!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!***************************************/
//		for (Packman pacman : game.pacmanSet) /*הדפסת מסלול של כל פקמן*/
//		{
////			System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
//			while(game.fruitSet.size()>0)
//			{
////				myFrame.repaint();
//
//				Fruit fruit =pacman.getP_Path().getFruitsPath().get(0) ;
//				 distanceToFruit = pacman.point_A_toPoint_B(fruit  ,  mapW,  mapH , game);
//				 double time  = pacman.TimeToFruit(fruit);
//				 
//						System.out.println("distance = " +distanceToFruit );
//				 if( distanceToFruit < 1 )
//				 {
//					
//						System.out.println(	 "PACMAN "+game.pacmanSet.get(game.pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ game.pacmanSet.get(game.pacmanSet.indexOf(pacman)).getP_Path().toString());
//						counter++;
//					    System.out.println("fruit "  +pacman.getP_Path().getFruitsPath().get(0).getId() + "was eaten!!!" );
//					
//						if(fruit.getId() == pacman.getP_Path().getFruitsPath().get(0).getId())
//						{
//							game.fruitSet.remove(fruit);
//							 pacman.getP_Path().getFruitsPath().remove(0);
//							 game.removefFruits(pacman);
////							 spa = new ShortestPathAlgo(game , mapW , mapH ); /*גורם להעלמה של פקמנים*/
//							
//						}
//						
////						System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
//						 try {
//								sleep(1000);;
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//					}
//				
//				 }
//			myFrame.repaint();
//System.out.println("out of while loop");
//			}
//	}
}

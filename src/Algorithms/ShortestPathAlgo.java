package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;

public class ShortestPathAlgo 
{
//
//	public  ShortestPathAlgo(Game game)
//	{
//		int minimumPath ;
//		int minPacmanIndex ;
//		double tempMinTime ;
//		double minTime  ;
//		
//		for(Fruit fruit : game.fruitSet )
//		{
//			minTime = Double.MAX_VALUE ;
//			minPacmanIndex = 0 ;
//		
//			for (Packman pacman : game.pacmanSet) 
//			{
//				tempMinTime =pacman.TimeToFruit(fruit) ;
//				
//				
//				if(minTime == tempMinTime)
//				{
//					minPacmanIndex = shortestPath(game, minPacmanIndex , game.pacmanSet.indexOf(pacman) ) ;
//					
//				}
//				else if(minTime > tempMinTime)
//				{
//					minTime = tempMinTime ;
//					minPacmanIndex =  game.pacmanSet.indexOf(pacman); 
//				}
//			}
//			game.pacmanSet.get(minPacmanIndex).getP_Path().addToPath(fruit);		
//		}
//		
//	}
//	private int shortestPath(Game game , int minPacmanIndex , int tempPacmanIndex )
//	{
//		int minPacmanPathSize = game.pacmanSet.get(minPacmanIndex).getP_Path().getFruitsPath().size();
//		int tempPacmanPathSize =game.pacmanSet.get(tempPacmanIndex).getP_Path().getFruitsPath().size();
//		
//		
//		if(minPacmanPathSize > tempPacmanPathSize)
//		{
//			return tempPacmanIndex ;
//		}
//		else
//		{
//			return minPacmanIndex ;
//		}
//	}
	
	public ShortestPathAlgo(Game game)
	{
		Fruit chosenFruit ;
		for (Packman pacman : game.pacmanSet) 
		{
			int minfruitIndex = 0 ;
			double tempDistance ;
			double minDistance = Double.MAX_VALUE  ;
			for (Fruit fruit : game.fruitSet) 
			{
				tempDistance = pacman.TimeToFruit(fruit) ;
				if(minDistance > tempDistance)
				{
					minDistance = tempDistance ;
					minfruitIndex = game.fruitSet.indexOf(fruit);
				}
			}
			chosenFruit = game.fruitSet.get(minfruitIndex);
			pacman.getP_Path().getFruitsPath().add(chosenFruit) ;
			chosenFruit.setChosedToPath();
		}
		
		for (Fruit mainFruit : game.fruitSet) 
		{
			if(mainFruit.getChosedToPath() == false)
			{
				int minPacmanIndex = 0 ;
				int pathSize ;
				double tempDistance ;
				double minDistance = Double.MAX_VALUE  ;
				
				Fruit fruit ;
				for (Packman pacman : game.pacmanSet) 
				{
					pathSize = pacman.getP_Path().getFruitsPath().size() ;
					fruit = pacman.getP_Path().getFruitsPath().get(pathSize - 1) ;
					tempDistance = mainFruit.distanceToFruit(fruit);
					if(minDistance > tempDistance)
					{
						minDistance = tempDistance ;
						minPacmanIndex = game.pacmanSet.indexOf(pacman);
					}
				}
				Packman pacman = game.pacmanSet.get(minPacmanIndex);
				pacman.getP_Path().getFruitsPath().add(mainFruit);
				mainFruit.setChosedToPath();
			}
		}
//		for (Packman pacman : game.pacmanSet) 
//		{
//			System.out.println(pacman.getP_Path().toString());
//		}
	  }
		
		
	}
	
	


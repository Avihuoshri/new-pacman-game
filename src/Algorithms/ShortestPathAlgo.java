package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;

public class ShortestPathAlgo 
{

	public  ShortestPathAlgo(Game game)
	{
		int minimumPath ;
		int minPacmanIndex ;
		double tempMinTime ;
		double minTime  ;
		
		for(Fruit fruit : game.fruitSet )
		{
			minTime = Double.MAX_VALUE ;
			minPacmanIndex = 0 ;
		
			for (Packman pacman : game.pacmanSet) 
			{
				tempMinTime =pacman.TimeToFruit(fruit) ;
				
				
				if(minTime == tempMinTime)
				{
					minPacmanIndex = shortestPath(game, minPacmanIndex , game.pacmanSet.indexOf(pacman) ) ;
					
				}
				else if(minTime > tempMinTime)
				{
					minTime = tempMinTime ;
					minPacmanIndex =  game.pacmanSet.indexOf(pacman); 
				}
			}
			game.pacmanSet.get(minPacmanIndex).getP_Path().addToPath(fruit);		
		}
		
	}
	private int shortestPath(Game game , int minPacmanIndex , int tempPacmanIndex )
	{
		int minPacmanPathSize = game.pacmanSet.get(minPacmanIndex).getP_Path().getFruitsPath().size();
		int tempPacmanPathSize =game.pacmanSet.get(tempPacmanIndex).getP_Path().getFruitsPath().size();
		
		
		if(minPacmanPathSize > tempPacmanPathSize)
		{
			return tempPacmanIndex ;
		}
		else
		{
			return minPacmanIndex ;
		}
	}
	
	
	
}

package Algorithms;

import java.awt.Point;
import java.util.ArrayList;

import GUI.pacmanBoard;
import Game_figures.Game;
import Game_figures.Path;
import Geom.Point3D;

public class BoxVertexPath extends Point3D
{
	Path boxPath ;
	
	Game game ;
	
	
	
	
	public BoxVertexPath()
	{
		super(0,0,0) ;
	}
	
	
	private int linear(Point3D player , Point3D fruit , int x)
	{
		double incline = (player.iy() - fruit.iy()) / (player.ix() - fruit.ix());
		int y = ( int )incline*(x - player.ix()) + player.ix() ;
		return y ;
	}
	public static void main(String[] args) {
	}

}

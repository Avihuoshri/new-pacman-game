package GUI;

import java.util.ArrayList;

import Game_figures.Fruit;
import Geom.Point3D;

public class Path 
{
	
	 ArrayList<Fruit> fruitsPath = new ArrayList();
	 ArrayList<ArrayList<Point3D>>fullPath = new ArrayList<ArrayList<Point3D>>();
	 ArrayList<Point3D>player_F_Path = new ArrayList();
	 ArrayList<Point3D>player_B_Path = new ArrayList();

	
	public ArrayList<Fruit> getFruitsPath() {
		return fruitsPath;
	}

	public Path()
	{
	
	}
	
	public Path(ArrayList<Point3D>PathSet)
	{
		fullPath.add(PathSet);
	}
	public void addToPath(Fruit fruit)
	{
		fruitsPath.add(fruit) ;
	}
	
	public void addPlayer_B_Path(Point3D vertix)
	{
		player_B_Path.add(vertix) ;
	}
	public String toString()
	{
		String path ="" ;
		for (Fruit fruit : fruitsPath) 
		{
		 path += fruit.getId() + "--->" ;
		}
		return path ;
	}
	
}

package GUI;

import java.util.ArrayList;

import Game_figures.Fruit;
import Geom.Point3D;

public class Path 
{
	
	 ArrayList<Fruit> fruitsPath = new ArrayList();
	 ArrayList<ArrayList<Point3D>>fullPath = new ArrayList<ArrayList<Point3D>>();
	
	
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

package Game_figures;

import java.util.ArrayList;

import Geom.Point3D;

public class Path 
{
	
	private ArrayList<Fruit> fruitsPath = new ArrayList();
	
	public Path()
	{
	
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
	
	public ArrayList<Fruit> getFruitsPath() {
		return fruitsPath;
	}
}

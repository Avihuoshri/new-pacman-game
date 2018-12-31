package Game_figures;

import java.util.ArrayList;

import Geom.Point3D;

public class Path 
{
	
	 ArrayList<Fruit> fruitsPath = new ArrayList();
	
	
	public ArrayList<Fruit> getFruitsPath() {
		return fruitsPath;
	}

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
	
}

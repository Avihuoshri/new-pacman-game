package Game_figures;

import java.util.ArrayList;

import Algorithms.BoxVertexPath;
import Geom.Point3D;

public class Path 
{
	
	private ArrayList<Fruit> fruitsPath = new ArrayList();
	private ArrayList<Point3D> BoxPath = new ArrayList();
	
	/**
	 * default constractor
	 */
	
	public Path()
	{
	
	}
	
	/**
	 * add the fruit to the path	
	 * @param fruit
	 */
	
	public void addToPath(Fruit fruit)
	{
		fruitsPath.add(fruit) ;
	}
	
	public void addToBoxPath(BoxVertexPath bvp)
	{
		BoxPath.add(bvp) ;
	}
	
	/**
	 * to string
	 */
	public String toString()
	{
		String path ="" ;
		for (Fruit fruit : fruitsPath) 
		{
		 path += fruit.getId() + "--->" ;
		}
		return path ;
	}
	
	/**
	 * get the fruits path
	 * @return
	 */
	public ArrayList<Fruit> getFruitsPath() {
		return fruitsPath;
	}
}

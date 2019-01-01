package Game_figures;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Algorithms.ShortestPathAlgo;
import Algorithms.createGameFromCsv;
import Coords.Map;
import Geom.Point3D;

public class Game 
{
	final int COLUMNS_NUM = 7 ;
	public ArrayList<Box> boxSet =new  ArrayList();
	public ArrayList<Ghost> ghostSet = new ArrayList();
	public ArrayList<Packman> pacmanSet = new ArrayList();
	public ArrayList<Fruit> fruitSet =new  ArrayList();

	
	private createGameFromCsv cgf ;
	
	public Game()
	{
		
	}
	
	public Game(String filePath)   /*בדקתי ועובד טוב*/
	{
		Map map = new Map() ;
		File game = new File(filePath) ;
		Point3D pixels ;
		Point3D coordinates ;
		Point3D boxCoordinates ;
		FileReader fr;
		String[] elements = new String[COLUMNS_NUM] ;
		int id ;
		double altitude ;
		double latitude ;
		double longtitude ;
		double speed ;
		double radius ;
		if(filePath.length() > 0)
		{
			try 
			{
				fr = new FileReader(game);
				BufferedReader br = new BufferedReader(fr);
				
					String line = br.readLine() ;
					while((line = br.readLine()) != null)
					{
						elements = line.split(",");
						id = Integer.parseInt(elements[1]) ;
						latitude = Double.parseDouble(elements[2]);
						longtitude = Double.parseDouble(elements[3]);		
						altitude = Double.parseDouble(elements[4]) ;
						speed = Double.parseDouble(elements[5]) ;
						coordinates = new Point3D(latitude , longtitude);
						pixels = new Point3D(map.coordsToPixels(coordinates)) ; /*המרת הנקודה מקורדינטות לפיקסלים*/
						 
						
						if(elements[0].equals("P"))
						{
							radius = Double.parseDouble(elements[6]) ;
							System.out.println("pacman\t" +id +"\tX-acsis -->\t " +  pixels.ix()  + "\t   Y-acsis -->\t " +pixels.iy() + "\taltitude\t" + altitude+ "\tlongtitude\t"+longtitude );
							pacmanSet.add(new Packman(id, pixels.ix(), pixels.iy() , speed  )) ;
						}
						
						else if(elements[0].equals("F"))
						{
					System.out.println("fruit\t" +id +" \tX-acsis -->\t" + pixels.ix() + "\t   Y-acsis -->\t" +pixels.iy() + "\taltitude\t" + altitude +  "\tlongtitude\t"+longtitude);
							fruitSet.add(new Fruit(id, pixels.ix(), pixels.iy() , "Images//Batman_logo.png"));
						}
						
						else if(elements[0].equals("B"))
						{
							radius = Double.parseDouble(elements[6]) ;
							boxCoordinates = new Point3D(speed ,radius );
							boxCoordinates = map.coordsToPixels(boxCoordinates);
							boxSet.add(new Box(pixels , boxCoordinates)) ;
						}
						
						else if(elements[0].equals("G"))
						{
							radius = Double.parseDouble(elements[6]) ;
							ghostSet.add(new Ghost(id , speed , pixels)) ;
							
						}
						
					}	 
					
			} 	
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	

	
	public void removefFruits(Packman pacman)
	{
		while(pacman.getP_Path().getFruitsPath().size() > 0)
		{
			pacman.getP_Path().getFruitsPath().remove(0);
		}
	}

}

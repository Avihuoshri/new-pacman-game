package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

import javax.imageio.ImageIO;

import Coords.CoordsConverter;
import Coords.Map;
import Geom.Point3D;

public class Fruit 
{
	final int WEIGHT = 1 ;                  
	final int NUMBER_OF_MONSTERS = 4;
	private int f_id ;
	private int randomIndex ;
	private Point3D fruitLocation ;
	private Point3D p_Location ;
	private File fruitFile ;
	private BufferedImage fruitImage ;
	private String[] monstersImages ;
	private Random random ;
	boolean chosedToPath  ;
	
	
	/**
	 * constractor with input
	 * @param fruitId
	 * @param x_pixel
	 * @param y_pixel
	 * @param imagePath
	 */
	
	public Fruit(int fruitId , double x_pixel , double y_pixel ,String imagePath)
	{
		
		this.f_id = fruitId ;	
		p_Location = new Point3D(x_pixel , y_pixel );                  /*בפיקסלים*/ 
		chosedToPath = false ;
		if(imagePath.length() > 0)
		{
			fruitFile = new File(imagePath);
			try {
				fruitImage = ImageIO.read(fruitFile) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{
			{
		}
		    monstersImages = new String[NUMBER_OF_MONSTERS] ;
			monstersImages[0] = "Images/Avihu.png" ;
			monstersImages[1] = "Images/Sapir.png" ;
			monstersImages[2] = "Images/Amir.png" ;
			monstersImages[3] = "Images/yellow monster.png" ;
			Random rand = new Random() ;
			int index = rand.nextInt(4);
			fruitFile = new File(monstersImages[index]);
			try {
				fruitImage = ImageIO.read(fruitFile) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @param get fruit
	 * @return the distance to the fruit
	 */

	public double distanceToFruit(Fruit fruit)      /*בדקתי ועובד טוב*/
	{	double pythagoreanDistance ;
	int sharedXPoint = Math.abs(this.getFruitLocation().ix() - fruit.getFruitLocation().ix()) ;
	int sharedYPoint = Math.abs(this.getFruitLocation().iy() - fruit.getFruitLocation().iy()) ;

	sharedXPoint *= sharedXPoint ;
	sharedYPoint *= sharedYPoint ;
	pythagoreanDistance = Math.sqrt(  sharedXPoint + sharedYPoint ) ;
	return pythagoreanDistance ;
	}
	
	/**
	 * 
	 * @return the fruit lication
	 */
	public Point3D getFruitLocation()
	{
		return p_Location ;
	}
	
	/**
	 * 
	 * @return the weight of the fruit
	 */
	public int getWEIGHT() 
	{
		return WEIGHT ;
	}
	
	/**
	 * 
	 * @return the id of the fruit
	 */
	public int  getId()
	{
		return f_id ;
	}
	
	/** 
	 * @return the image
	 */
	public BufferedImage getFruitImage() 
	{
		return fruitImage;
	}
	
	/**
	 * set the fruit in the path
	 */
	public void setChosedToPath()
	{
		chosedToPath = true ;
	}
	/**
	 * 
	 * @return true if the fruit chose to path
	 * false else
	 */
	public boolean getChosedToPath()
	{
		return chosedToPath ;
	}
}

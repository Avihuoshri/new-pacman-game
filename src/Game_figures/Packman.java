package Game_figures;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Coords.CoordsConverter;
import Coords.Map;
import GUI.pacmanBoard;
import Geom.Point3D;

public class Packman extends Thread
{
	private final int p_speed = 1 ;                   /************************************************** לשים לב למהירות!!!! *************************************************/
	private final int EATTING_RADIUS = 1 ;
	private int p_id ;
	private int p_Weight ;
	private int animationIndex  = 0 ;
	private Point3D c_loction ;
	private Point3D pixel_loction ;
	private Path p_Path ;
	Path p ;
	private File p_FileImage  ;
	CoordsConverter converter ;
	private BufferedImage p_Image ;
	Map map = new Map() ;
	ArrayList<String> animatedbatman = new ArrayList() ;
	
	public Packman(int pacmanId , double pixel_X , double pixel_Y , double speed   )
	{
		p_id = pacmanId ; 
		pixel_loction =new Point3D(pixel_X , pixel_Y  ) ;    /*בפיקסלים*/
		converter = new CoordsConverter() ;
		p_Path = new Path() ;
		 setAnimation() ;
		p_FileImage = new File(animatedbatman.get(animationIndex));
		if(animationIndex == 8)
		{
			animationIndex=0 ;
		}
		try 
		{
			p_Image = ImageIO.read(p_FileImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		for(int i = 0 ; i <400 ; i++)
		{
		Point3D pacmanPoint = new Point3D(this.getP_Location()) ;
		Point3D point = new Point3D(pacmanPoint.ix()+1 ,pacmanPoint.iy());
		setPixelLocation( point) ;
		}
	}
	
	private void setAnimation()
	{
		for(int i = 1 ; i<8 ; i++)
		{
			animatedbatman.add("Images//Animated batman//"+i+".png");
		}
	}
	public double point_A_toPoint_B(Fruit destination , int mapWidth , int mapHeight , Game game )
	{
		double x , y  ;
		Map map = new Map() ;
		CoordsConverter converter = new CoordsConverter() ;
		Point3D pacmanPoint = new Point3D(map.pixelToCoords(this.getP_Location().ix(), this.getP_Location().iy(), mapWidth, mapHeight)) ;                            /* in GPS coordinates */
		Point3D fruitPoint = new Point3D(map.pixelToCoords(destination.getFruitLocation().ix(), destination.getFruitLocation().iy(), mapWidth, mapHeight)) ;   /*  in GPS coordinates */
		double distance = converter.distance3d(pacmanPoint, fruitPoint) ;
		double time = distance / this.getP_speed() ;		

		return time ;
	}
	
	
	public double TimeToFruit( Fruit  fruit) /*בדקתי ועובד טוב*/
	{
		double pToF_Distance = distanceToFruit(fruit) ;
		double time = pToF_Distance / this.getP_speed();
		return time ;
	}

	private double distanceToFruit(Fruit fruit)      /*בדקתי ועובד טוב*/
	{	double pythagoreanDistance ;
	int sharedXPoint = Math.abs(this.getP_Location().ix() - fruit.getFruitLocation().ix()) ;
	int sharedYPoint = Math.abs(this.getP_Location().iy() - fruit.getFruitLocation().iy()) ;

	sharedXPoint *= sharedXPoint ;
	sharedYPoint *= sharedYPoint ;
	pythagoreanDistance = Math.sqrt(  sharedXPoint + sharedYPoint ) ;
	return pythagoreanDistance ;
	}
	
	public Path getP_Path()
	{
		return p_Path;
	}
	
	public Point3D getP_Location()
	{
		return pixel_loction ;
	}
	
	public int getP_id()
	{
		return p_id ;
	}
	
	public int getP_speed()
	{
		return p_speed ;
	}
	
	public int getEATTING_RADIUS()
	{
		return EATTING_RADIUS ;
	}
	
	public BufferedImage getP_Image() 
	{
		p_FileImage = new File(animatedbatman.get(animationIndex));
		animationIndex++ ;
		if(animationIndex == 7)
		{
			animationIndex=0 ;
		}
		try 
		{
			p_Image = ImageIO.read(p_FileImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p_Image;
	}
	
	public void setPixelLocation(Point3D pixelPoint)
	{
		this.pixel_loction = new Point3D(pixelPoint) ;
	}
}

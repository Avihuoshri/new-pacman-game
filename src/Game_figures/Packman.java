package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Coords.CoordsConverter;
import Coords.Map;
import GUI.pacmanBoard;
import Geom.Point3D;

public class Packman extends Thread
{
	private final int p_speed = 1 ;                   /************************************************** ���� �� �������!!!! *************************************************/
	private final int EATTING_RADIUS = 1 ;
	private int p_id ;
	private int p_Weight ;
	private Point3D c_loction ;
	private Point3D pixel_loction ;
	private Path p_Path ;
	Path p ;
	private File p_FileImage  ;
	CoordsConverter converter ;
	private BufferedImage p_Image ;
	Map map = new Map() ;
	
	public Packman(int pacmanId , double pixel_X , double pixel_Y , double speed   )
	{
		p_id = pacmanId ; 
		pixel_loction =new Point3D(pixel_X , pixel_Y  ) ;    /*��������*/
		converter = new CoordsConverter() ;
		p_Path = new Path() ;
		p_FileImage = new File("Images//Batman.png");
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
	
	public double point_A_toPoint_B(Fruit destination , int mapWidth , int mapHeight , Game game )
	{
		double x , y  ;
		Map map = new Map() ;
		CoordsConverter converter = new CoordsConverter() ;
		Point3D pacmanPoint = new Point3D(map.pixelToCoords(this.getP_Location().ix(), this.getP_Location().iy(), mapWidth, mapHeight)) ;                            /* in GPS coordinates */
		Point3D fruitPoint = new Point3D(map.pixelToCoords(destination.getFruitLocation().ix(), destination.getFruitLocation().iy(), mapWidth, mapHeight)) ;   /*  in GPS coordinates */
		double distance = converter.distance3d(pacmanPoint, fruitPoint) ;
		double time = distance / this.getP_speed() ;		
		if(distance > 1)
		{
			Point3D vectorInMeters = new Point3D(converter.vector3D(pacmanPoint, fruitPoint)) ;
			x = vectorInMeters.ix() / time ;
			y= vectorInMeters.iy() / time  ;
			vectorInMeters = new Point3D(x,y);
//			System.out.println( " vectorInMeters    ---> " +vectorInMeters);
			
			Point3D pacman_new_Point = new Point3D(converter.add(pacmanPoint, vectorInMeters)) ;
			Point3D pixelPoint = new Point3D(map.coordsToPixels(pacman_new_Point)) ;
			this.setPixelLocation(destination.getFruitLocation());
		}
		else
		{
			this.setPixelLocation(destination.getFruitLocation());
			game.fruitSet.remove(destination) ;		
		}
		return time ;
	}
	
	
	public double TimeToFruit( Fruit  fruit) /*����� ����� ���*/
	{
		double pToF_Distance = distanceToFruit(fruit) ;
		double time = pToF_Distance / this.getP_speed();
		return time ;
	}

	private double distanceToFruit(Fruit fruit)      /*����� ����� ���*/
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
		return p_Image;
	}
	
	public void setPixelLocation(Point3D pixelPoint)
	{
		this.pixel_loction = new Point3D(pixelPoint) ;
	}
}

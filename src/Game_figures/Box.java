package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Box 
{

	
	Point3D upper_R_Point ,upper_L_Point , point;
	Point3D lower_L_Point , lower_R_Point ; 
	
	int boxHeight , boxWidth ;
	private File img_file;
	private BufferedImage bi;
	Path boxPath ;
	public Box(Point3D p) /*receive point with PIXELS values*/
	{
		point = new Point3D(p);
	}
	public Box(Point3D p1 ,Point3D p2) /*receive point with PIXELS values*/
	{
		setPoints(p1,p2) ;
		boxWidth  = Width(p1 , p2);
		boxHeight = Height(p1, p2) ;
		
		img_file = new File("Images//bricks-wall.jpg ");
		try {
			bi = ImageIO.read(img_file) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getBoxHeight() {
		return boxHeight;
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public void setPoints(Point3D p1 ,Point3D p2)
	{
		if(p1.iy() > p2.iy())
		{
			upper_R_Point = p2 ;
			lower_L_Point = p1 ;
			lower_R_Point = new Point3D(upper_R_Point.x() , lower_L_Point.y());
			upper_L_Point = new Point3D(lower_L_Point.x() , upper_R_Point.y());
		}
		else
		{
			upper_R_Point = p1;
			lower_L_Point = p2;
			
			lower_R_Point = new Point3D(upper_R_Point.x() , lower_L_Point.y());
			upper_L_Point = new Point3D(lower_L_Point.x() , upper_R_Point.y());
		}
		
		
		
	}
	
	public BufferedImage getBoxImage()
	{
		return bi ;
	}
	private int Width(Point3D p1 ,Point3D p2)
	{
		return Math.abs(p1.ix() - p2.ix()) ;
	}
	
	private int Height(Point3D p1 ,Point3D p2)
	{
		return Math.abs(p1.iy() - p2.iy()) ;

	}
	public Point3D getUpperPoint() {
		return upper_R_Point;
	}

	public Point3D getLowerPoint() {
		return lower_L_Point;
	}
	public Point3D getPoint() {
		return point;
	}

	public Point3D getUpper_R_Point() {
		return upper_R_Point;
	}
	public Point3D getUpper_L_Point() {
		return upper_L_Point;
	}
	public Point3D getLower_L_Point() {
		return lower_L_Point;
	}
	public Point3D getLower_R_Point() {
		return lower_R_Point;
	}
}

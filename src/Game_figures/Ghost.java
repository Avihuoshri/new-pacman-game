package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Coords.CoordsConverter;
import Coords.Map;
import Geom.Point3D;

public class Ghost 
{
	final int NUMBER_OF_GHOSTS = 2;
	Point3D G_point ;
	double G_speed ;
	int G_Id ;
	File img_file ;
	BufferedImage bi ;
	private String[] monstersImages ;
	private Random random ;

	public Ghost(int id , double speed , Point3D point )
	{
		G_Id = id ;
		G_speed = speed ;
		G_point = new Point3D(point);
		monstersImages = new String[NUMBER_OF_GHOSTS] ;
		monstersImages[0] = "Images/Joker.png" ;
		monstersImages[1] = "Images/Harly_quinn.png" ;
		Random rand = new Random() ;
		int index = rand.nextInt(2);
		img_file = new File(monstersImages[index]);
		try {
			bi = ImageIO.read(img_file) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		

	}

	
	public double distanceToPlayer(Player player , Ghost ghost)      /*בדקתי ועובד טוב*/
	{	double pythagoreanDistance ;
	int Xrange = Math.abs(player.getPlayerLocation().ix() - ghost.getG_point().ix()) ;
	int Yrange = Math.abs(player.getPlayerLocation().iy() - ghost.getG_point().iy()) ;

	Xrange *= Xrange ;
	Yrange *= Yrange ;
	pythagoreanDistance = Math.sqrt(  Xrange + Yrange ) ;
	return pythagoreanDistance ;
	}
	public BufferedImage getG_image()
	{
		return bi ;
	}
	public Point3D getG_point()
	{
		return G_point ;
	}

	public void setG_point(Point3D g_point) {
		G_point = new Point3D(g_point);
	}
}

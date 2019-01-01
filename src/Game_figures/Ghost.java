package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
public BufferedImage getG_image()
{
	return bi ;
}
	public Point3D getG_point()
	{
		return G_point ;
	}
}

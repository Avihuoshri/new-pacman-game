package Game_figures;

import Geom.Point3D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Coords.CoordsConverter;
import Game_figures.Fruit ;
public class Player 
{
	private Point3D player_location ;
	
	private Point3D direction ;
	private int animationIndex  = 0 ;
	Path p ;
	private File p_FileImage  ;
	CoordsConverter converter ;
	private BufferedImage p_Image ;
	ArrayList<String> animatedbatman = new ArrayList() ;
	
	public Player()
	{
		
	}
	public Player(Point3D firstPoint)
	{
		player_location = new Point3D(firstPoint);
//		player_location = new Point3D(400,400);
		direction = new Point3D(firstPoint) ;
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

	private void setAnimation()
	{
		for(int i = 1 ; i<8 ; i++)
		{
			animatedbatman.add("Images//Animated Batman//"+i+".png");
		}
	}
	
	public double distanceToFruit(Player player , Fruit fruit)      /*בדקתי ועובד טוב*/
	{	double pythagoreanDistance ;
	int Xrange = Math.abs(player.getPlayerLocation().ix() - fruit.getFruitLocation().ix()) ;
	int Yrange = Math.abs(player.getPlayerLocation().iy() - fruit.getFruitLocation().iy()) ;

	Xrange *= Xrange ;
	Yrange *= Yrange ;
	pythagoreanDistance = Math.sqrt(  Xrange + Yrange ) ;
	return pythagoreanDistance ;
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
	public Point3D getPlayerLocation()
	{
		return player_location;
	}
	
	public Point3D getDirection() {
		return direction;
	}

	public void setPlayerLocation(Point3D newPoint)
	{
		player_location = new Point3D(newPoint);
	}
	
	public void setDirection(Point3D newDPoint)
	{
		direction = new Point3D(newDPoint);
	}
}

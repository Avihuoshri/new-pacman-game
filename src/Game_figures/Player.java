package Game_figures;

import Geom.Point3D;

public class Player 
{
	private Point3D player_location ;
	
	private Point3D direction ;

	public Player(Point3D firstPoint)
	{
		player_location = new Point3D(firstPoint);
		player_location = new Point3D(400,400);
		direction = new Point3D(firstPoint) ;
	}
	
	public Point3D getPlayerLocation()
	{
		return player_location;
	}
	
	public void setPlayerLocation(Point3D newPoint)
	{
		player_location = new Point3D(newPoint);
	}
	
	public void setPlayerDirection(Point3D newDPoint)
	{
		direction = new Point3D(newDPoint);
	}
}

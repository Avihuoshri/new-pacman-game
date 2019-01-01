package Game_figures;

import Geom.Point3D;

public class Box 
{

	
	Point3D upperPoint , lowerPoint , point;
	int boxHeight , boxWidth ;
	public Box(Point3D p) /*receive point with PIXELS values*/
	{
		point = new Point3D(p);
	}
	public Box(Point3D p1 ,Point3D p2) /*receive point with PIXELS values*/
	{
		setPoints(p1,p2) ;
		boxWidth  = Width(p1 , p2);
		boxHeight = Height(p1, p2) ;
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
			upperPoint = p2 ;
			lowerPoint = p1 ;
		}
		else
		{
			upperPoint = p1;
			lowerPoint = p2;
		}

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
		return upperPoint;
	}

	public Point3D getLowerPoint() {
		return lowerPoint;
	}
	public Point3D getPoint() {
		return point;
	}
	
}

package Geom;

import Coords.CoordsConverter;
/**
 * 
 * @author Orel Bracha Avihu Oshri
 * In this class we are creating the methods of distances between 2D points and 3D points 
 * as well as the needed constructors
 *
 */
public class GeomE implements Geom_element 
{
	final double LON_NORM = 0.847091174 ;
	Point3D point;
	
	/**
	 * 
	 * @param point3d - a copy constructor that gets this point as a whole
	 */
	public GeomE(Point3D point3d)
	{
	 point = new Point3D(point3d);
	}
	/**
	 * 
	 * @param x - lat coordinate of the point
	 * @param y - lon coordinate of the point
	 *
	 * @param z - alt coordinate of the point
	 */
	public GeomE( double x , double y , double z)
	{
	 point = new Point3D(x,y,z);
	}
	
	@Override
	/**
	 * Computing the 3D distance between two points 
	 */
	public double distance3D(Point3D p) 
	{
		CoordsConverter coord = new CoordsConverter() ;
		return coord.distance3d(point, p)  ;
		
	}
    /**
     * Computing the 3D distance between two points 
     */
	@Override
	public double distance2D(Point3D p) 
	{
		CoordsConverter coord = new CoordsConverter() ;
		Point3D distance2D  = coord.twoPointsToMetter(point, p)  ;
		double distance2DX = Math.pow(distance2D.x(), 2) ;
		double distance2DY = Math.pow(distance2D.y(), 2) ;
		
		double distance = Math.sqrt(distance2DX + distance2DY ) ;
		return distance ;
		
	}

}

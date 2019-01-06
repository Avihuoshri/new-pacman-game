package Coords;

import java.awt.Point;
import java.awt.image.BufferedImage;

import Geom.Point3D;
/**
 * 
 * @author Avihu Oshri Orel Bracha
 * The class Map includes all the methods used to make the right convert decision
 * that helps us to get input with different types of values and translate them
 * correctly.
 */
public class Map 
{
	
	        final int mapWidth = 1350, mapHeight = 642;
			
			 final double mapLongitudeStart =35.20238  , mapLatitudeStart = 32.106046;
			
			 final double mapLongitude = 35.21236-mapLongitudeStart; 
			        
			    double mapLatitude = mapLatitudeStart-32.101658;
			Point3D RightDown = new Point3D(32.101073, 35.211922);
			Point3D LeftUp    = new Point3D(32.106199,35.201708);
			
			/**
			 * default constructor
			 */
			public Map()
			{
				
			}
			/**
			 * default constructor 
			 * @param mapWidth - received width of the map
			 * @param mapHeight - received height of the map
			 */
			public Map(int mapWidth,int mapHeight)
			{
				
			}
			/**
			 * 
			 * @param coordinates - A point on the surface which will be received with coordinate values.
			 * @return the value of the point - in pixel, calculated with a mathematical formula.
			 */
			public Point3D coordsToPixels(Point3D coordinates)//The source code:https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
			{
			    double x,y;
			    x=coordinates.y() - mapLongitudeStart;
			   
			    y = mapLatitudeStart-coordinates.x();
			  
			    int coordsLat = (int) (mapWidth*(x/mapLongitude));
			    int coordsLon = (int) (mapHeight*(y/mapLatitude));
		        if(coordsLat<0)
		        {
		        	coordsLat+=360;
		        }
		        if(coordsLon<0)
		        {
		        	coordsLon+=360;
		        }
			    return new Point3D(coordsLat,coordsLon);

			}
			/**
			 * 
			 * @param PointWidth - the X value of the point in pixels
			 * @param PointHeight - the Y value of the point in pixels
			 * @param widthMap - the width of the map we will receive
			 * @param hightMap - the height of the map we will receive
			 * @return - after making the convert with the requested calculations, we will return the point with suitable 
			 *           coordinate values.
			 */
			public Point3D pixelToCoords(int PointWidth, int PointHeight,int widthMap,int hightMap) {
				double coordsY = (((hightMap-(double)PointHeight)*RightDown.y())+((double)PointHeight*LeftUp.y()))/hightMap;
				double coordsX = (((widthMap-(double)PointWidth)*RightDown.x())+((double)PointWidth*LeftUp.x()))/widthMap;
				Point3D result =new Point3D(coordsX,coordsY);
				
				return result;
			}

		/**
		 * 
		 * @param p1 - first point we will receive (GPS)
		 * @param p2 - second point we will receive (GPS)
		 * @return - distance in meters between the points 
		 */
			
		public double PixDist(Point3D p1, Point3D p2)
		{
			Point3D p1Pixels =coordsToPixels(p1);
			Point3D p2Pixels =coordsToPixels(p2);
			double distanceMeters = p1Pixels.distance3D(p2Pixels);
			return distanceMeters;
		}
		/**
		 * 
		 * @param point1 - first point we will receive (GPS)
		 *
		 * @param point2 - second point we will receive (GPS)
		 * @return - The angle values located between the points   
		 */
		public double angleBetween(Point3D point1, Point3D point2)
		{
			Point3D p1Pixels =coordsToPixels(point1);
			Point3D p2Pixels =coordsToPixels(point2);
			double angle = Math.toDegrees(Math.atan2(point2.x()-point1.x(),point2.y()-point1.y()));
			if(angle<0)
				angle+=360;
			
			if(angle>180)
				angle=360-angle;
				return angle;
		}

	public static void main(String[] args) {
		Map map = new Map() ;
		System.out.println(map.coordsToPixels(new Point3D(32.1054140278181 ,35.2033024988153)));
	}

}

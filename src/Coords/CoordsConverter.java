package Coords;

import Geom.Point3D;

public class CoordsConverter implements coords_converter{

	private final static double radius = 6371000;
	private final static double lon = 0.84709;

/**
 * add local_vetor_in_meter(x,y,z in meters) to Point3D point
 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D newpoint = meter2polar(local_vector_in_meter);
		newpoint.add(gps);
		return newpoint;
	}
/**
 * convet from meter to polar
 * @param vec3deg
 * @return
 */
	public Point3D meter2polar(Point3D vec3deg) {
		double x = Math.toDegrees(Math.asin(vec3deg.x()/radius));
		double y = Math.toDegrees(Math.asin(vec3deg.y()/(radius*lon)));

		return new Point3D(x,y,vec3deg.z());
	}



/**
 * calculate the distance betweek 2 diffrent points
 */
@Override
public double distance3d(Point3D gps0, Point3D gps1) {
	double x,y,r,l,d;
	r=6371000;
	l=Math.cos(gps0.x()*Math.PI/180);
	x=gps1.x()-gps0.x();
	x=x*Math.PI/180;
	x=Math.sin(x)*r;
	y=gps1.y()-gps0.y();
	y=y*Math.PI/180;
	y=Math.sin(y)*l*r;
	d=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	return d;
}
/**
 * return point 3d between 2 points
 */
@Override
public Point3D vector3D(Point3D gps0, Point3D gps1) {		
	double lon_norm = Math.cos(Math.toRadians(gps0.x()));
	double x0 = radius*Math.sin(Point3D.d2r(gps1.x()-gps0.x()));
	double y0 = radius*lon_norm*Math.sin(Point3D.d2r(gps1.y()-gps0.y()));
	double z0 = gps1.z()-gps0.z();
	
	return new Point3D(x0,y0,z0);
}
/**
 * create array with the info of azimuth elevation and distance
 */

@Override
public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

	double x=gps1.x()-gps0.x();
	double y=gps1.y()-gps0.y();
	double azimuth=Math.atan2(Math.sin(Math.toRadians(y))*Math.cos(Math.toRadians(gps1.x())), Math.cos(Math.toRadians(gps0.x()))*Math.sin(Math.toRadians(gps1.x()))-Math.sin(Math.toRadians(gps0.x()))*Math.cos(Math.toRadians(gps1.x()))*Math.cos(Math.toRadians(y)));
	azimuth=Math.toDegrees(azimuth);
	while (azimuth<0){ azimuth=azimuth+360;}		
	double dist=distance3d(gps0,gps1);
	double elevation=Math.asin((gps1.z()-gps0.z())/dist);
	elevation=Math.toDegrees(elevation);
	double arr[]= {azimuth,elevation,dist};
	return arr;
}
/**
 * check if the point is vaild on the gps
 */
@Override
public boolean isValid_GPS_Point(Point3D p) {
	if(p.x()<-180 || p.x()> 180) return false;
	if(p.y()<-90 || p.y()> 90) return false;
	if(p.z()<-450) return  false;

	return true;
}
}
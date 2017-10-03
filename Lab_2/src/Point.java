/*
 *      Author: Malcolm Groves 
 *      Point Interface
 *      19 September 2016
 *
 *      This interface is implemented by the Point2D class.
 *      It has functionalities for 2-dimensional points.
 *
 */
public interface Point {

	// get utilities
	public Point getCopy();
	public double getX();
	public double getY();
	
	// set utilities
	public void setPoint(double x, double y);
	public void setX(double x);
	public void setY(double y);
	
	// general toString method
	public String toString();
	
}

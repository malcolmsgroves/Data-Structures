/*
 *      Author: Malcolm Groves 
 *      Point2D Class
 *      19 September 2016
 *
 *      This class is a 2-dimensional point class. The only
 *      instance variables are the x and y coordinates. The
 *      Point2D class implements the Point interface.
 *
 */


public class Point2D implements Point {

	protected double x;
	protected double y;

	// constructor methods

	public Point2D() {
		x = 0.0;
		y = 0.0;
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Purpose: Generate a deep copy of the Point2D object
	// Parameters: NA
	// Return: Copy of the Point2D object
	public Point2D getCopy() {

		Point2D copyPoint = new Point2D(this.x, this.y);

		return copyPoint;
	}

	
	// set-get utilities are self-documenting
	
	public double getX() {

		return this.x;
	}


	public double getY() {

		return this.y;
	}


	public void setPoint(double x, double y) {
		
		this.x = x;
		this.y = y;
	}


	public void setX(double x) {

		this.x = x;
	}

	public void setY(double y) {

		this.y = y;
	}

	// general toString utility
	public String toString() {

		return "Point2D[x = " + x + ", y = " + y + "]";
	}

}

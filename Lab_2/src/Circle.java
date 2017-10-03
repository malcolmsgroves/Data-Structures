/*
 *      Author: Malcolm Groves (adapted from Stephen Majercik's code)
 *      Circle Class
 *      19 September 2016
 *
 *      This is a circle class with instance variables for the radius 
 *      and the 2D coordinates of the center. The class includes methods
 *      to return the area of the circle.
 *
 */


public class Circle {
	
	protected double radius;
	protected Point2D center;
	
	// constructors
	
	public Circle() {
		
		radius = 0.0;
		center = new Point2D();
	}
	
	public Circle(double radius, Point2D center) {
		
		this.setRadius(radius);
		
		// copy the Point2D object so it is not overwritten
		this.center = center.getCopy();
	}
	
	// get-set utilities: mostly self-documenting
	
	public double getRadius() {
		return radius;
	}
	
	public Point2D getCenter() {
		return center;
	}
	
	public void setRadius(double radius) {
		
		// make sure the radius is non-negative
		if (radius >= 0) {
			this.radius = radius;
		}
		else{
			System.out.println("error: radius cannot be negative");
		}
	}
	
	public void setCenter(Point2D center) {
		this.center = center.getCopy();
	}

	// Purpose: Calculate the area of the circle object
	// Parameters: NA
	// Return: Area of the circle.
	public double getArea() {
		return radius * radius * Math.PI;
	}
	
	// General toString method
	public String toString() {
		return "Circle[radius = " + radius + ", " + 
				center.toString() + "]";
	}
	

}

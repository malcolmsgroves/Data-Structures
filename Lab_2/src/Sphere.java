/*
 *      Author: Malcolm Groves 
 *      Sphere Class
 *      19 September 2016
 *
 *      This is a sphere class with instance variables for the 
 *      3D coordinates of the center. The class includes methods
 *      to return the surface area and volume of the sphere. This
 *      class extends the circle class and inherits many of the
 *      same methods
 *
 */
public class Sphere extends Circle {
	
	private Point3D center;
	
	// constructors that inherit functionality from Circle
	
	public Sphere() {
		
		super.setRadius(0.0);;
		center = new Point3D();
	}
	
	public Sphere(double radius, Point3D center) {
		
		super.setRadius(radius);
		this.setCenter(center);;
	}
	
	//general get-set utilities for center: self-documenting
	//
	public Point3D getCenter() {
		return this.center;
	}
	
	public void setCenter(Point3D center) {
		Point3D centerCopy = center.getCopy();
		this.center.setPoint(centerCopy.getX(), centerCopy.getY(), 
				centerCopy.getZ());
	}

	// Return surface area of the sphere
	public double getArea() {
		return 4 * super.radius * super.radius * Math.PI;
	}
	
	// Return volume of the sphere
	public double getVolume() {
		
		return 4 * Math.PI * Math.pow(super.radius, 3) / 3;
	}
	
	// general toString method that encapsulates the Point3D toString method
	public String toString() {
		return "Sphere[radius = " + super.radius + ", " + 
				center.toString() + "]";
	}
}

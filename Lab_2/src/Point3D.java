/*
 *      Author: Malcolm Groves 
 *      Point3D Class
 *      19 September 2016
 *
 *      This class is a 3-dimensional point class. The class
 *      extends the Point2D class. The only instance variable
 *      is the z coordinate, the other coordinates are inherited
 *      from Point2D.
 *
 */

public class Point3D extends Point2D {

	protected double z;
	
	//constructors that inherit Point2D constructors
	
	public Point3D() {
		
		super(); 
		z = 0.0;
	}
	
	public Point3D(double x, double y, double z) {
		
		super(x, y); 
		this.z = z;
	}

	// Purpose: Generate a deep copy of the Point3D object
	// Parameters: NA
	// Return: Copy of the Point3D object
	public Point3D getCopy() {
		
		Point3D copyPoint = new Point3D(super.getX(), super.getY(), this.z);
		
		return copyPoint;
	}

	// get-set utilities are self-documenting
	
	public double getX() {
		
		return super.getX();
	}


	public double getY() {
		
		return super.getY();
	}

	public double getZ() {
		
		return this.z;
	}

	public void setPoint(double x, double y, double z) {
		super.setPoint(x,  y);
		this.z = z;

	}

	public void setX(double x) {
		
		super.setX(x);

	}

	public void setY(double y) {
		
		super.setY(y);
	}
	
	public void setZ(double z) {
		
		this.z = z;
	}
	
	// general toString utility
	public String toString() {
		
		return "Point3D[x = " + super.getX() + ", y = " + 
				super.getY() + ", z = " + z + "]";
	}

}

	


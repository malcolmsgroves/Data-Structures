/*
 * 		Author: Malcolm Groves
 * 		TestPoint Class
 * 		20 September 2016
 * 
 * 		This class tests some of the functionalities of
 * 		the Point2D, Point3D, Circle, and Sphere classes.
 * 		It is not exhaustive and has no real function.
 */
public class TestPoint {

	public static void main(String[] args) {
		
		Sphere ball = new Sphere();
		System.out.println(ball.getRadius());  // try sphere constructor
		
		Point3D center = new Point3D(4, 3, 2);
		ball.setRadius(-4); // make sure radius cannot be negative
		ball.setRadius(4);
		ball.setCenter(center);
		
		// try other Point3D and Sphere methods
		System.out.println(ball.toString());
		System.out.println("Volume: " + ball.getVolume());
		System.out.println("Area: " + ball.getArea());
		System.out.println(center.toString());
	}
}

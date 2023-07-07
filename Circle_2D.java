package ex4.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;

	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public Circle_2D(Circle_2D c) {
		this(c.getCenter(), c.getRadius());
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	    public String toString()
	    {
			////// add your code here //////
			return _center.toString()+", "+_radius;
			//return null;
			////////////////////////////////
		}
	@Override
	public boolean contains(Point_2D ot) {
		double dist = ot.distance(this._center);
		return dist<=this._radius;



	}

	@Override
	public double area() {
		double ans = Math.PI * Math.pow(this._radius, 2);
		return ans;
	}
	@Override
	public double perimeter() {
		double ans = Math.PI * 2 * this._radius;
		return ans;
	}
	@Override
	public void translate(Point_2D vec) {

		_center.move(vec);
	}


	@Override
	public GeoShape copy() {
		return new Circle_2D(_center, _radius);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if (center == null || ratio == 1 ||ratio <= 0) {    // invalid inputs stop
			System.err.println("invalid input");
			return;
		}

		this._center.scale(center, ratio);

		this._radius *= ratio;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		////// add your code here //////
		this._center.rotate(center, angleDegrees);
		////////////////////////////////
	}

	@Override
	public Point_2D[] getPoints() {
		return new Point_2D[0];
	}

}

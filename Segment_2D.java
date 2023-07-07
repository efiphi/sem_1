package ex4.geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private  Point_2D p1;
	private  Point_2D p2;
	public Segment_2D(Point_2D a, Point_2D b) {
		this.p1 = new Point_2D(a);
		this.p2 = new Point_2D(b);

	}
	public Segment_2D(Segment_2D t1) {

	}

	public Point_2D get_p1() {
		return this.p1;
	}
	public Point_2D get_p2() {
		return this.p2;
	}
	@Override
	public String toString()
	{
		return this.p1.toString()+", "+this.p2.toString();
	}
	@Override
	public boolean contains(Point_2D ot) {

		return p1.distance(ot) + ot.distance(p2) == p1.distance(p2);
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {

		return p1.distance(p2);
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
	}

	@Override
	public GeoShape copy() {

		return new Segment_2D(p1, p2);
	}
	@Override
	public void scale(Point_2D center, double ratio) {
		this.p1.scale(center, ratio);
		this.p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
	}

	@Override
	public Point_2D[] getPoints() {
		Point_2D[] ans = {p1,p2};

		return ans;
	}
}
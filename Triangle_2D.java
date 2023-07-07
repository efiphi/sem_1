package ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D[] _points;

	public Triangle_2D(Point_2D[] points) {
		this._points = points;
	}



	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < _points.length; i++) {
			str += _points.length-1 == i ?_points[i].toString() :_points[i].toString()+",";
		}

		return str;
	}
	@Override
	public boolean contains(Point_2D ot) {
		Point_2D p0 = this._points[0];
		Point_2D p1 = this._points[1];
		Point_2D p2 = this._points[2];


		var s = (p0.x() - p2.x()) * (ot.y() - p2.y()) - (p0.y() - p2.y()) * (ot.x() - p2.x());
		var t = (p1.x() - p0.x()) * (ot.y() - p0.y()) - (p1.y() - p0.y()) * (ot.x() - p0.x());

		if ((s < 0) != (t < 0) && s != 0 && t != 0)
			return false;

		var d = (p2.x() - p1.x()) * (ot.y() - p1.y()) - (p2.y() - p1.y()) * (ot.x() - p1.x());
		return d == 0 || (d < 0) == (s + t <= 0);
	}

	@Override
	public double area() {
		Point_2D p0 = this._points[0];
		Point_2D p1 = this._points[1];
		Point_2D p2 = this._points[2];

		return Math.abs((p0.x() * (p1.y() - p2.y()) + p1.x() * (p2.y() - p0.y()) + p2.x() * (p0.y() - p1.y())) / 2.0);
	}

	@Override
	public double perimeter() {
		Point_2D p0 = this._points[0];
		Point_2D p1 = this._points[1];
		Point_2D p2 = this._points[2];

		return p0.distance(p1) + p0.distance(p2) + p2.distance(p1);
	}

	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].move(vec);
		}
	}

	@Override
	public GeoShape copy() {

		Point_2D[] points = new Point_2D[this._points.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point_2D(this._points[i].x(), this._points[i].y());
		}

		return new Triangle_2D(points);
	}
	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].rotate(center, angleDegrees);
		}
	}

	@Override
	public Point_2D[] getPoints() {
		Point_2D[] ans = this._points;

		return ans;
	}
}

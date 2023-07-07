package ex4.geo;

public class Polygon_2D implements GeoShape{

	private Point_2D[] _points;

	public Polygon_2D(Point_2D[] points) {

		this._points = points;


	}
	public Polygon_2D(Polygon_2D po) {

	}
	public Point_2D[] getAllPoints() {


		return this._points;

	}

	public void add(Point_2D p) {

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
		double minX = this._points[0].x();
		double maxX = this._points[0].x();
		double minY = this._points[0].y();
		double maxY = this._points[0].y();
		for ( int i = 1 ; i < this._points.length ; i++ )
		{
			Point_2D q = this._points[ i ];
			minX = Math.min( q.x(), minX );
			maxX = Math.max( q.x(), maxX );
			minY = Math.min( q.y(), minY );
			maxY = Math.max( q.y(), maxY );
		}

		if ( ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY ){
			return false;
		}


		boolean inside = false;
		for ( int i = 0, j = this._points.length - 1 ; i < this._points.length ; j = i++ )
		{
			if ( ( this._points[ i ].y() > ot.y() ) != ( this._points[ j ].y() > ot.y() ) &&
					ot.x() < ( this._points[ j ].x() - this._points[ i ].x() ) * ( ot.y() - this._points[ i ].y() ) / ( this._points[ j ].y() - this._points[ i ].y() ) + this._points[ i ].x() )
			{
				inside = !inside;
			}
		}



		return inside;
	}

	@Override
	public double area() {
		double sum = 0;
		for (int i = 0; i < this._points.length ; i++)
		{
			if (i == 0)
			{
				System.out.println(this._points[i].x() + "x" + (this._points[i + 1].y() + "-" + this._points[this._points.length - 1].y()));
				sum += this._points[i].x() * (this._points[i + 1].y() - this._points[this._points.length - 1].y());
			}
			else if (i == this._points.length - 1)
			{
				System.out.println(this._points[i].x() + "x" + (this._points[0].y() + "-" + this._points[i - 1].y()));
				sum += this._points[i].x() * (this._points[0].y() - this._points[i - 1].y());
			}
			else
			{
				System.out.println(this._points[i].x() + "x" + (this._points[i + 1].y() + "-" + this._points[i - 1].y()));
				sum += this._points[i].x() * (this._points[i + 1].y() - this._points[i - 1].y());
			}
		}

		double area = 0.5 * Math.abs(sum);



		return area;
	}
	@Override
	public double perimeter() {
		double distance = 0;
		int len = this._points.length;
		for(int i = 0; i < len; i++) {
			distance += this._points[i].distance(this._points[(i+1)%len]);
		}
		return distance;
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
			points[i] = new Point_2D(this._points[i].x(),this._points[i].y());
		}

		return new Polygon_2D(points);
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
		return this._points;
	}

}

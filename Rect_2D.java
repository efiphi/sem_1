package ex4.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
    public class Rect_2D implements GeoShape {
	private double HalfWidth;
	private double HalfHeight;
	private Point_2D[] _points;
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D cen;
	public Rect_2D(Point_2D p1, Point_2D p2) {
		////// add your code here //////

		////////////////////////////////
	}
	public Rect_2D(Rect_2D t1) {
		////// add your code here //////

		////////////////////////////////
	}

    public Rect_2D(Point_2D[] points) {
		this._points = points;
		this.p1 = points[0];
		this.p2 = points[2];
    }
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < _points.length; i++) {
			str += _points.length-1 == i ?_points[i].toString() :_points[i].toString()+",";
		}

		return str;
	}

	public Point_2D getCen() {
		this.cen = new Point_2D((this.p1.x() + this.p2.x())/2,(this.p1.y() + this.p2.y())/2);

		return this.cen;
	}

	public double getHalfWidth() {
		this.HalfWidth = Math.abs(p1.x() - this.getCen().x());

		return this.HalfWidth;
	}
	public double getHalfHeight() {
		this.HalfHeight = Math.abs(p1.y() - this.getCen().y());
		return this.HalfHeight;
	}

    @Override
	public boolean contains(Point_2D ot) {

		// TODO Auto-generated method stub
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
		double ans = (2*this.getHalfHeight())*(2*this.getHalfWidth());

		return ans;
	}

	@Override
	public double perimeter() {
		double ans = ((2*this.getHalfHeight())+(2*this.getHalfWidth()))*2;
		return ans;
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

		return new Rect_2D(points);
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
		// TODO Auto-generated method stub
		Point_2D[] ans = this._points;

		return ans;
	}
}

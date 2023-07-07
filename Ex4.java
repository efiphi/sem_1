package ex4.ex4;

import ex4.geo.Circle_2D;
import ex4.geo.GeoShape;
import ex4.geo.Point_2D;
import ex4.gui.Ex4_GUI;
import ex4.gui.GUIShape;
import ex4.gui.GUI_Shape;
import ex4.gui.StdDraw_Ex4;
import ex4.geo.Rect_2D;
import ex4.geo.Polygon_2D;
import ex4.geo.Segment_2D;
import ex4.geo.Triangle_2D;
import ex4.geo.ShapeComp;



import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.FileDialog;
import java.awt.Frame;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private static final String Int = null;
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1;
	
	private  static Ex4 _winEx4 = null;
	private ArrayList<Point_2D> trianglePoints = new ArrayList<Point_2D>();
	private  ArrayList<Point_2D> polygonPoints = new ArrayList<Point_2D>();
	private static ArrayList<Point_2D> rectPoints = new ArrayList<Point_2D>();
	private Comparator<GUI_Shape> coperator;
	
	private Ex4() {
			init(null);
	}
	public void init(GUI_Shape_Collection s) {
		if(s==null) {_shapes = new ShapeCollection();
		}
		else {_shapes = s;}// //shou,ld be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		 _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	
	public void drawShapes() {
		StdDraw_Ex4.clear();
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape sh = _shapes.get(i);
				
				drawShape(sh);
			}
			if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D)gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if(gs instanceof Rect_2D) {
			Rect_2D r = (Rect_2D)gs;
			Point_2D[] points = r.getPoints();

			double[] x = new double[points.length];
			double[] y = new double[points.length];
			for (int i = 0; i < points.length; i++) {
				x[i] = points[i].x();
				y[i] = points[i].y();
			}

			if(isFill) {
				StdDraw_Ex4.filledPolygon(x,y);
			}
			else {
				StdDraw_Ex4.polygon(x,y);


			}
		}

		if(gs instanceof Segment_2D) {
			Segment_2D r = (Segment_2D)gs;
			Point_2D p1 = r.getPoints()[0];
			Point_2D p2 = r.getPoints()[1];

			StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());

		}

		if(gs instanceof Triangle_2D) {
			Triangle_2D t = (Triangle_2D)gs;
			Point_2D[] points = t.getPoints();
			double[] x =  new double[points.length];
			double[] y =  new double[points.length];

			for (int i = 0; i < points.length; i++) {
				x[i] = points[i].x();
				y[i] = points[i].y();
			}


			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else {
				StdDraw_Ex4.polygon(x,y);
			}

		}

		if(gs instanceof Polygon_2D) {
			Polygon_2D p = (Polygon_2D)gs;
			Point_2D[] points = p.getPoints();

			double[] x =  new double[points.length];
			double[] y =  new double[points.length];

			for (int i = 0; i < points.length; i++) {
				x[i] = points[i].x();
				y[i] = points[i].y();
			}

			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);;
			}
			else {
				StdDraw_Ex4.polygon(x, y);
			}
		}

	}
		

	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {
			_shapes.removeAll();
			rectPoints.clear();
			trianglePoints.clear();
			polygonPoints.clear();
		}
		if(p.equals("Load")) {load();}

		if(p.equals("Save")) {save();}

		if(p.equals("Remove")) {
			for(int i= _shapes.size()-1; i >= 0 ;i--) {
				GUI_Shape s = _shapes.get(i);
				GeoShape g = s.getShape();
				if(g!=null && s.isSelected()) {
					_shapes.removeElementAt(i);

				}
			}
		}

		if(p.equals("All")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(true);
			}
		}


		if(p.equals("None")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(false);

			}
		}

		if(p.equals("Anti")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(!s.isSelected());
			}
		}

		if(p.equals("Empty")) {
			if(_gs!=null) {
				_gs.setFilled(false);
			}
		}


		if(_mode.equals("Info")) {
			System.out.println(this.getInfo());

		}
		if(_mode.equals("ByArea")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Area);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiArea")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByPerimeter")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiPerimeter")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByToString")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_toString);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiToString")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByTag")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Tag);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiTag")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
			_shapes.sort(coperator);
		}

		if(_mode.equals("Info")) {
			System.out.println(this.getInfo());

		}

	
		
		drawShapes();
		
	}

	
	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Rect")) {
//
			if(_gs==null) {
				_p1 = new Point_2D(p);
				rectPoints.add(_p1);

				rectPoints.add(p);
				rectPoints.add(p);
				rectPoints.add(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_shapes.size()+1);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				rectPoints.clear();
			}
		}


		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_shapes.size()+1);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
				if(trianglePoints.size() == 0){
					trianglePoints.add(_p1);
					trianglePoints.add(p);
				}
			}else{
				if(trianglePoints.size() == 3 && trianglePoints.get(1).distance(trianglePoints.get(2)) != 0) {
					_gs.setColor(_color);
					_gs.setFilled(_fill);
					_gs.setTag(_shapes.size()+1);
					_shapes.add(_gs);
					_gs = null;
					_p1 = null;
					trianglePoints.clear();


				}
				if(trianglePoints.size() == 2 && trianglePoints.get(0).distance(p) != 0 ) {
					trianglePoints.add(p);
				}

			}
		}


		if(_mode.equals("Polygon")){
			if(_gs==null) {
				_p1 = new Point_2D(p);
				polygonPoints.add(_p1);
				polygonPoints.add(p);
			}
			else {
				if(polygonPoints.get(polygonPoints.size()-1).distance(p) != 0) {
					polygonPoints.clear();
				}
				polygonPoints.add(p);
			}
		}
			if(_mode.equals("Move")) {
				if(_p1==null) {_p1 = new Point_2D(p);}
				else {
					_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
					move();
					_p1 = null;
				}
			}
		if(_mode.equals("Scale_90%")){
			scale(p,0.9);

		}

		if(_mode.equals("Scale_110%")){
			scale(p,1.1);
		}

		if(_mode.equals("Rotate")){
			if(_p1==null) {_p1 = new Point_2D(p);}

			else {
				double difX = p.x() - _p1.x();
				double difY = p.y() - _p1.y();
				double angle = Math.toDegrees(Math.atan2(difY, difX));
				rotate(_p1,angle);

				_p1 = null;

			}


		}


		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point_2D(p);}
			else {
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;

			}
		}
	
		if(_mode.equals("Point")) {
			select(p);
		}
	
		drawShapes();
	}
	private void load() {
		try {
			FileDialog fileChooser = new FileDialog(new Frame(),"Load from Text file", FileDialog.LOAD);
			fileChooser.setVisible(true);
			File file = fileChooser.getFiles()[0];
			_shapes.load(file.getCanonicalPath());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void save() {
		try {
			FileDialog fileChooser = new FileDialog(new Frame(),"Save to Text file", FileDialog.SAVE);
			fileChooser.setVisible(true);
			System.out.println(fileChooser.getDirectory().toString()+fileChooser.getFile());
			_shapes.save(fileChooser.getDirectory().toString()+fileChooser.getFile());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void select(Point_2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}
	private void copy() {
		for(int i=0;i<_shapes.size();i++) {
			if(_shapes.get(i).isSelected()) {
				GUI_Shape origin = _shapes.get(i);
				GUI_Shape copy = origin.copy();
				copy.getShape().translate(_p1);
				_shapes.add(copy);

			}
		}
	}
	
	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
		GeoShape gs = null;
		if (_mode != "Polygon") {
			_gs = new GUIShape(gs, false, Color.pink, 0);
			rectPoints.clear();
			trianglePoints.clear();
		} else {
			polygonPoints.remove(polygonPoints.size() - 1);
			Point_2D[] points = polygonPoints.toArray(new Point_2D[polygonPoints.size()]);
			gs = new Polygon_2D(points);
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_gs.setTag(_shapes.size() + 1);
			_gs = new GUIShape(gs, _gs.isFilled(), _gs.getColor(), _gs.getTag());
			_shapes.add(_gs);
			polygonPoints.clear();

		}
		drawShapes();
		_p1 = null;
		_gs = null;
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
		//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			if(_mode.equals("Rect")) {

				rectPoints.set(1, new Point_2D(p.x(),_p1.y()));
				rectPoints.set(2,p);
				rectPoints.set(3,new Point_2D(_p1.x(),p.y()));

				Point_2D[] points = rectPoints.toArray(new Point_2D[rectPoints.size()]);
				gs = new Rect_2D(points);
			}

			if(_mode.equals("Segment")) {

				gs = new Segment_2D(_p1,p);

			}

			if(_mode.equals("Triangle")) {
				trianglePoints.set(1, p);
				Point_2D[] points = trianglePoints.toArray(new Point_2D[trianglePoints.size()]);

				gs = new Triangle_2D(points);

			}

			if(_mode.equals("Polygon")) {
				polygonPoints.set(polygonPoints.size()-1, p);
				Point_2D[] points = polygonPoints.toArray(new Point_2D[polygonPoints.size()]);
				gs = new Polygon_2D(points);

			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
	private void scale(Point_2D p, double ratio) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.scale(p,ratio);
			}
		}
	}

	private void rotate(Point_2D p, double angle) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.rotate(p, angle);

			}
		}
	}
}

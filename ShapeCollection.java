package ex4.ex4;

import ex4.geo.*;
import ex4.gui.GUIShape;
import ex4.gui.GUI_Shape;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private final ArrayList<GUI_Shape> _shapes;


	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		return _shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(i, s);
		}
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		return new ShapeCollection();
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try {
			//Whatever the file path is.
			File filePath = new File(file);
			FileOutputStream is = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(is);

			Writer w = new BufferedWriter(osw);

			for (int i = 0; i < _shapes.size(); i++) {
				_shapes.get(i).setTag(i);
				GeoShape s = _shapes.get(i).getShape();

				String joined =s.toString();
				System.out.println(joined);
				w.write(_shapes.get(i).getClass().getSimpleName()+","+_shapes.get(i).getColor().getRGB()+","+_shapes.get(i).isFilled()+","+_shapes.get(i).getTag()+","+s.getClass().getSimpleName()+","+joined+"\n");

			}

			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file");
		}

	}

	@Override
	public void load(String file) {
		GeoShape gs = null;
		_shapes.clear();

		try {
			Scanner scanner = new Scanner(new File(file));
			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				String[] line = currentLine.split(",");

				if(currentLine.contains("Circle_2D")) {
					gs = new Circle_2D(new Point_2D(Double.parseDouble(line[5]),Double.parseDouble(line[6])), Double.parseDouble(line[7]));
					GUIShape _g = new GUIShape(gs, Boolean.parseBoolean(line[2]), new Color(Integer.parseInt(line[1])), Integer.parseInt(line[3]));
					_shapes.add(_g);
				}else {
					String[] polygon = currentLine.split(",");
					String[] strPoints = Arrays.copyOfRange(polygon, 5, polygon.length);
					Point_2D[] points = new Point_2D[strPoints.length/2];

					for (int i = 0; i < strPoints.length/2; i++) {
						points[i] = new Point_2D(Double.parseDouble(strPoints[2*i]),Double.parseDouble(strPoints[(2*i)+1]));
					}

					gs = new Polygon_2D(points);
					GUIShape _gs = new GUIShape(gs, currentLine.contains("Segment_2D") ? false : Boolean.parseBoolean(line[2]), new Color(Integer.parseInt(line[1])), Integer.parseInt(line[3]));
					_shapes.add(_gs);
				}

				System.out.println(Arrays.toString(currentLine.split(",")));
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}

	@Override
	public Rect_2D getBoundingBox() {
		Rect_2D ans = null;
		LinkedList<Point_2D> all_points= new LinkedList<>();

		for (int i = 0; i < _shapes.size(); i++) {									//go over all the shapes
			Point_2D[] shape_p = new Point_2D[_shapes.get(i).getShape().getPoints().length+2];

			if(_shapes.get(i).getShape() instanceof Circle_2D) {//if circle
				shape_p[0] =(_shapes.get(i).getShape()).getPoints()[0];
				shape_p[1] =(_shapes.get(i).getShape()).getPoints()[1];
				double rad = shape_p[0].distance(shape_p[1]);
				shape_p[2]= new Point_2D(shape_p[0].x(), shape_p[0].y()-rad);
				shape_p[3]= new Point_2D(shape_p[0].x()+rad, shape_p[0].y());
				shape_p[0]= new Point_2D(shape_p[0].x()-rad, shape_p[0].y());

			}else {
				shape_p =_shapes.get(i).getShape().getPoints();}//if not

			for (int j = 0; j < shape_p.length; j++) {								//extract all the points
				all_points.push(shape_p[j]);
			}
		}
		double Xmax = all_points.get(0).x();
		double Xmin = all_points.get(0).x();
		double Ymax = all_points.get(0).y();
		double Ymin = all_points.get(0).y();
		for (int i = 1; i < all_points.size(); i++) {
			Xmax = Math.max(Xmax, all_points.get(i).x());
			Xmin = Math.min(Xmin, all_points.get(i).x());
			Ymax = Math.max(Ymax, all_points.get(i).y());
			Ymin = Math.min(Ymin, all_points.get(i).y());
		}
		Point_2D p1 = new Point_2D(Xmin, Ymax);
		Point_2D p2 = new Point_2D(Xmax, Ymax);
		Point_2D p3 = new Point_2D(Xmax, Ymin);
		Point_2D p4 = new Point_2D(Xmin, Ymin);
		Point_2D[] points = {p1,p2,p3,p4};
		ans = new Rect_2D(points);

		return ans;

	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}

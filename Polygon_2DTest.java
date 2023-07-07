package ex4.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {

    public final Point_2D _p1 = new Point_2D(1,1);
    public final Point_2D _p2 = new Point_2D(3,3);
    public final Point_2D _p3 = new Point_2D(1,3);
    public final Point_2D _p4 = new Point_2D(-1,3);
    public final Point_2D _p5 = new Point_2D(-1,1);


    public Point_2D[] points = new Point_2D[5];
    public Point_2D[] points_copy = new Point_2D[5];

    public void set_array() {
        points[0] = new Point_2D(_p1);
        points[1] = new Point_2D(_p2);
        points[2] = new Point_2D(_p3);
        points[3] = new Point_2D(_p4);
        points[4] = new Point_2D(_p5);

        points_copy[0] = new Point_2D(_p1);
        points_copy[1] = new Point_2D(_p2);
        points_copy[2] = new Point_2D(_p3);
        points_copy[3] = new Point_2D(_p4);
        points_copy[4] = new Point_2D(_p5);
    }

    public Polygon_2D _Polygon = new Polygon_2D(points);
    public Polygon_2D _copy = new Polygon_2D(points_copy);


    @BeforeEach
    public void beforeach() {
        set_array();
        _Polygon = new Polygon_2D(points);
        _copy = new Polygon_2D(points_copy);
    }


    @Test
    void testToString() {
        set_array();
        assertEquals("1.0,1.0,3.0,3.0,1.0,3.0,-1.0,3.0,-1.0,1.0", _Polygon.toString());
    }

    @Test
    void testContains() {
        set_array();
        Point_2D p2 = new Point_2D(2, 2.5);
        Point_2D p3 = new Point_2D(7, 5);

        assertTrue(this._Polygon.contains(p2));
        assertFalse(this._Polygon.contains(p3));

    }

    @Test
    void testArea() {
        set_array();
        assertEquals(_Polygon.area(),6, 0.0005);
    }

    @Test
    void testPerimeter() {
        set_array();
        assertEquals(_Polygon.perimeter(),10.828,0.005);
    }

    @Test
    void testTranslate() {
        set_array();
        Point_2D vec1 = new Point_2D(1, 1);
        Point_2D vec2 = new Point_2D(-2, 2);
        Point_2D vec3 = new Point_2D(7, 5);

        translatelooper(vec1);
        Triangle_2D move1 =new Triangle_2D(points); beforeach();
        translatelooper(vec2);
        Triangle_2D move2 =new Triangle_2D(points); beforeach();
        translatelooper(vec3);
        Triangle_2D move3 =new Triangle_2D(points); beforeach();

        this._Polygon.translate(vec1);
        assertEquals(_Polygon.toString(), move1.toString()); beforeach();
        this._Polygon.translate(vec2);
        assertEquals(_Polygon.toString(),move2.toString()); beforeach();
        this._Polygon.translate(vec3);
        assertEquals(_Polygon.toString(),move3.toString()); beforeach();
    }


    @Test
    void testCopy() {
        set_array();
        Polygon_2D Deep_copy = (Polygon_2D) _Polygon.copy();

        assertEquals(_Polygon.toString(),Deep_copy.toString());
    }

    @Test
    void testScale() {
        Point_2D center = new Point_2D(2, 2);
        double ratio = 2.0;

        _Polygon.scale(center, ratio);

        Point_2D[] expectedPoints = {
                new Point_2D(0, 0),
                new Point_2D(4, 4),
                new Point_2D(0, 4)
        };

        Point_2D[] actualPoints = _Polygon.getPoints();

        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i].x(), actualPoints[i].x(), 0.0001);
            assertEquals(expectedPoints[i].y(), actualPoints[i].y(), 0.0001);
        }

    }

    @Test
    void testRotate() {
        Point_2D center = new Point_2D(2, 2);
        double angleDegrees = 90.0;

        _Polygon.rotate(center, angleDegrees);

        Point_2D[] expectedPoints = {
                new Point_2D(3, 1),
                new Point_2D(1, 3),
                new Point_2D(1, 1)
        };

        Point_2D[] actualPoints = _Polygon.getPoints();

        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i].x(), actualPoints[i].x(), 0.0001);
            assertEquals(expectedPoints[i].y(), actualPoints[i].y(), 0.0001);
        }

    }

    @Test
    void getPoints() {
        Triangle_2D copy = new Triangle_2D(points);

        assertEquals(_Polygon.toString(),copy.toString());
    }
    public void translatelooper(Point_2D vec) {
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i].add(vec);
        }
    }
}
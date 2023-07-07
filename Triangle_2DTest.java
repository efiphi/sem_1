package ex4.geo;

import org.junit.jupiter.api.Test;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ex4.geo.Point_2D;
import ex4.geo.Segment_2D;


import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {
    public final Point_2D _p1 = new Point_2D(1,1);
    public final Point_2D _p2 = new Point_2D(3,3);
    public final Point_2D _p3 = new Point_2D(1,3);
    public Point_2D[] points = new Point_2D[3];
    public Point_2D[] points_copy = new Point_2D[3];
    public void set_array() {
        points[0] = new Point_2D(_p1);
        points[1] = new Point_2D(_p2);
        points[2] = new Point_2D(_p3);
        points_copy[0] = new Point_2D(_p1);
        points_copy[1] = new Point_2D(_p2);
        points_copy[2] = new Point_2D(_p3);
    }

    public Triangle_2D _triangle = new Triangle_2D(points);
    public Triangle_2D _copy = new Triangle_2D(points_copy);


    @BeforeEach
    public void beforeeach() {
        set_array();
        _triangle = new Triangle_2D(points);
        _copy = new Triangle_2D(points_copy);
    }

    @Test
     void testToString() {
        set_array();
        assertEquals("1.0,1.0,3.0,3.0,1.0,3.0", _triangle.toString());
    }

    @Test
     void testContains() {
        set_array();
        Point_2D p2 = new Point_2D(2, 2.5);
        Point_2D p3 = new Point_2D(7, 5);

        assertTrue(this._triangle.contains(p2));
        assertFalse(this._triangle.contains(p3));
    }

    @Test
     void testArea() {
        set_array();
        assertEquals(_triangle.area(),2, 0.0005);
    }

    @Test
    void testPerimeter() {
        set_array();
        assertEquals(_triangle.perimeter(),6.828,0.005);
    }

    @Test
    void testTranslate() {
        set_array();
        Point_2D vec1 = new Point_2D(1, 1);
        Point_2D vec2 = new Point_2D(2, 2);
        Point_2D vec3 = new Point_2D(7, 5);

        translatelooper(vec1);
        Triangle_2D move1 =new Triangle_2D(points); beforeeach();
        translatelooper(vec2);
        Triangle_2D move2 =new Triangle_2D(points); beforeeach();
        translatelooper(vec3);
        Triangle_2D move3 =new Triangle_2D(points); beforeeach();

        this._triangle.translate(vec1);
        assertEquals(_triangle.toString(), move1.toString()); beforeeach(); beforeeach();
        this._triangle.translate(vec2);
        assertEquals(_triangle.toString(),move2.toString()); beforeeach();
        this._triangle.translate(vec3);
        assertEquals(_triangle.toString(),move3.toString()); beforeeach();
    }

    @Test
    void testCopy() {
        set_array();
        assertEquals(_triangle.toString(),_triangle.copy().toString());
    }

    @Test
    void testScale() {
        Point_2D center = new Point_2D(1, 1);
        double ratio = 2.0;

        _triangle.scale(center, ratio);

        Point_2D[] expectedPoints = {
                new Point_2D(1, 1),
                new Point_2D(5, 5),
                new Point_2D(1, 5)
        };

        assertArrayEquals(expectedPoints, _triangle.getPoints());
    }

    @Test
    void testRotate() {
        Point_2D center = new Point_2D(1, 1);
        int angleDegrees = 90;

        _triangle.rotate(center, angleDegrees);

        Point_2D[] expectedPoints = {
                new Point_2D(1, 1),
                new Point_2D(-1, 3),
                new Point_2D(-1, 1)
        };

        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i].x(), _triangle.getPoints()[i].x(), 0.0001);
            assertEquals(expectedPoints[i].y(), _triangle.getPoints()[i].y(), 0.0001);
        }
    }

    @Test
    void testGetPoints() {
        Triangle_2D copy = new Triangle_2D(points);

        assertEquals(_triangle.toString(),copy.toString());
    }

    ////looper
    public void translatelooper(Point_2D vec) {
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i].add(vec);
        }
    }


}
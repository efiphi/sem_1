package ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {

    @Test
    void testScale() {
        // Test case 1
        Point_2D point1 = new Point_2D(2, 3);
        Point_2D center1 = new Point_2D(0, 0);
        Point_2D point2 = new Point_2D(-1, 5);
        Point_2D center2 = new Point_2D(2, 2);
        Point_2D point3 = new Point_2D(0, 0);
        Point_2D center3 = new Point_2D(1, 1);
        Point_2D point4 = new Point_2D(-2, -2);
        Point_2D center4 = new Point_2D(-1, -1);

        double ratio1 = 2;
        double ratio2 = 0.5;
        double ratio3 = 3;
        double ratio4 = 0.5;

        point1.scale(center1, ratio1);
        point2.scale(center2, ratio2);
        point3.scale(center3, ratio3);
        point4.scale(center4, ratio4);

        assertEquals(4.0, point1.x(), 0.0001);
        assertEquals(6.0, point1.y(), 0.0001);
        assertEquals(0.5, point2.x(), 0.0001);
        assertEquals(3.5, point2.y(), 0.0001);
        assertEquals(-2.0, point3.x(), 0.0001);
        assertEquals(-2.0, point3.y(), 0.0001);
        assertEquals(-1.5, point4.x(), 0.0001);
        assertEquals(-1.5, point4.y(), 0.0001);


    }

    @Test
    void testRotate() {
        Point_2D point1 = new Point_2D(3, 4);
        Point_2D center1 = new Point_2D(0, 0);
        Point_2D center2 = new Point_2D(0, 0);
        Point_2D point2 = new Point_2D(1, 0);
        Point_2D point3 = new Point_2D(0, 2);
        Point_2D center3 = new Point_2D(1, 1);
        Point_2D point4 = new Point_2D(-1, -1);
        Point_2D center4 = new Point_2D(0, 0);

        double angleDegrees1 = 90;
        double angleDegrees2 = 45;
        double angleDegrees3 = 180;
        double angleDegrees4 = 270;

        point1.rotate(center1, angleDegrees1);
        point2.rotate(center2, angleDegrees2);
        point3.rotate(center3, angleDegrees3);
        point4.rotate(center4, angleDegrees4);

        assertEquals(-4.0, point1.x(), 0.0001);
        assertEquals(3.0, point1.y(), 0.0001);
        assertEquals(0.7071, point2.x(), 0.0001);
        assertEquals(0.7071, point2.y(), 0.0001);
        assertEquals(2.0, point3.x(), 0.0001);
        assertEquals(0.0, point3.y(), 0.0001);
        assertEquals(-1.0, point4.x(), 0.0001);
        assertEquals(1.0, point4.y(), 0.0001);
    }

}
package ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    @Test
     void testContains() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        // Test case 1: Point inside the segment
        Point_2D point1 = new Point_2D(4, 4);
        boolean contains1 = segment.contains(point1);
        assertEquals(true, contains1);

        // Test case 2: Point outside the segment
        Point_2D point2 = new Point_2D(1, 1);
        boolean contains2 = segment.contains(point2);
        assertEquals(false, contains2);

    }

    @Test
     void testArea() {
      Point_2D _p1 = new Point_2D(1,1);
      Point_2D _p2 = new Point_2D(3,3);
      Segment_2D _Segment = new Segment_2D(_p1,_p2);
        assertEquals(_Segment.area(),0, 0.0005);
    }

    @Test
     void testPerimeter() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        double perimeter = segment.perimeter();
        assertEquals(5.656854249492381, perimeter, 0.0001);

    }

    @Test
     void testTranslate() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        Point_2D translationVec = new Point_2D(2, 2);
        segment.translate(translationVec);

        Point_2D expectedP1 = new Point_2D(4, 4);
        Point_2D expectedP2 = new Point_2D(8, 8);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());

    }

    @Test
     void testCopy() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        Segment_2D copy = (Segment_2D) segment.copy();

        assertEquals(p1, copy.get_p1());
        assertEquals(p2, copy.get_p2());
    }

    @Test
     void testScale() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        Point_2D center = new Point_2D(4, 4);
        double ratio = 2;
        segment.scale(center, ratio);

        Point_2D expectedP1 = new Point_2D(0, 0);
        Point_2D expectedP2 = new Point_2D(8, 8);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());
    }

    @Test
     void testRotate() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        Point_2D center = new Point_2D(4, 4);
        double angleDegrees  = 90;
        segment.rotate(center, angleDegrees);

        Point_2D expectedP1 = new Point_2D(6, 2);
        Point_2D expectedP2 = new Point_2D(2, 6);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());
    }



    @Test
     void getPoints() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(6, 6);
        Segment_2D segment = new Segment_2D(p1, p2);

        Point_2D[] points = segment.getPoints();

        assertEquals(p1, points[0]);
        assertEquals(p2, points[1]);

    }
}
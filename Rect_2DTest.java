package ex4.geo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {
    Point_2D p1 = new Point_2D(4,4);
    Point_2D p2 = new Point_2D(8,4);
    Point_2D p3 = new Point_2D(8,2);
    Point_2D p4 = new Point_2D(4,2);
    Point_2D[] points = {p1,p2,p3,p4};
    Rect_2D rect = new Rect_2D(points);
    Point_2D cen = rect.getCen();
    @Test
     void testRect2D() {
        assertTrue(rect instanceof Rect_2D);

    }

    @Test
     void testToString() {
        assertEquals(rect.toString(),"4.0,4.0,8.0,4.0,8.0,2.0,4.0,2.0");
    }

    @Test
     void testGetCen() {
        assertEquals(rect.getCen(),new Point_2D(6,3));
    }

    @Test
     void testGetHalfWidth() {
        assertEquals(rect.getHalfWidth(),2);
    }

    @Test
     void testGetHalfHeight() {
        assertEquals(rect.getHalfHeight(),1);
    }

    @Test
     void testContains() {
        Point_2D  outside = new Point_2D(5,5);
        boolean ifinside = rect.contains(outside); // should return false

        Point_2D  inside = new Point_2D(6,3);
        boolean ifinside2 = rect.contains(inside); // should return true

        assertEquals(ifinside,false);
        assertNotSame(ifinside2,false);

    }

    @Test
     void testArea() {
        assertEquals(rect.area(),(2*rect.getHalfHeight())*(rect.getHalfWidth()*2));
    }

    @Test
     void testPerimeter() {
        assertEquals(rect.perimeter(),2*((rect.getHalfHeight()*2) +(rect.getHalfWidth()*2)));
    }
    @Test
     void testTranslate() {
        double addedValue = 4;

        Point_2D p1 = new Point_2D((cen.x()+addedValue )-cen.x(), cen.y() - cen.y());

        rect.translate(p1); // the cen should be equal to the new Point
        assertEquals(rect.getCen(),new Point_2D(cen.x()+addedValue ,cen.y()));
    }



    @Test
     void testCopy() {
        Rect_2D copy = (Rect_2D) rect.copy();
        assertEquals(copy.getHalfHeight(),rect.getHalfHeight());
        assertEquals(copy.getHalfWidth(),rect.getHalfWidth());
        assertEquals(copy.getCen(),rect.getCen());
        assertEquals(Arrays.toString(copy.getPoints()),Arrays.toString(rect.getPoints()));
    }

    @Test
     void testScale() {

        /////scaling with  ratio 1.1/////
        rect.scale(cen, 1.1);

        double height = 2*rect.getHalfHeight(); // after --> the height should be 2.2
        assertEquals(height,2.2,0.01);


        double width = 2*rect.getHalfWidth(); // after --> the width should be 4.4
        assertEquals(width,4.4,0.01);


        /////scaling with ratio 0.9/////

        rect.scale(cen, 0.9);

        double height2 = 2*rect.getHalfHeight(); // after --> the height should be 2.2*0.9
        assertEquals(height2,2.2*0.9,0.01);


        double width2 = 2*rect.getHalfWidth(); // after --> the width should be 4.4*0.9
        assertEquals(width2,4.4*0.9,0.01);

    }

    @Test
     void testRotate() {
        double height  = 2*rect.getHalfHeight();
        double  width  = 2*rect.getHalfWidth();

        // test when rotate by 90 degrees --> width = height and  height = width
        rect.rotate(cen, 90);

        assertEquals(2*rect.getHalfWidth() == height,true);
        assertEquals(2*rect.getHalfHeight() == width,false);

        // test when rotate by 180 degrees --> width = width and height = height
        rect.rotate(cen, 90); // using the prev rotated state

        assertEquals(2*rect.getHalfHeight() == height,true);
        assertEquals(2*rect.getHalfWidth() == width,true);
    }

    @Test
     void testGetPoints() {

        Point_2D p1 = new Point_2D(5,5);
        Point_2D p2 = new Point_2D(9,5);
        Point_2D p3 = new Point_2D(9,1);
        Point_2D p4 = new Point_2D(5,1);
        Point_2D[] rectPoints = {p1,p2,p3,p4};

        Rect_2D rect = new Rect_2D(rectPoints);

        assertEquals(Arrays.toString(rect.getPoints()), Arrays.toString(rectPoints));
    }

}
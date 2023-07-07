package ex4.ex4;

import static org.junit.jupiter.api.Assertions.*;

import ex4.geo.Point_2D;
import ex4.geo.Rect_2D;
import ex4.geo.ShapeComp;
import ex4.geo.Triangle_2D;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import ex4.gui.GUI_Shape;
import ex4.gui.GUIShape;
import org.junit.jupiter.api.Test;
import ex4.geo.Circle_2D;
import ex4.geo.GeoShape;

class ShapeCollectionTest {
    String path = "src/TestFile";
    private ShapeCollection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    GeoShape gs = null;
    private Comparator<GUI_Shape> coperator;
    Point_2D cen;

    @Test
    void testShapeCollection() {
        assertTrue(_shapes instanceof ShapeCollection );
    }

    @Test
    void testGet() {
        ////Circle///  ---> index 0
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle/// index 1
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

        assertTrue(_shapes.get(0).getShape() instanceof Circle_2D);
        assertTrue(_shapes.get(1).getShape() instanceof Triangle_2D);
    }

    @Test
    void testSize() {
        ////Circle///
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle///
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

//		  the size should be 2


        assertTrue(_shapes.size() == 2);


    }

    @Test
    void testRemoveElementAt() {
        ////Circle///  ---> index 0
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle/// index 1
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

        // so if we will remove the shape  at index 0 we should get only the triangle

        _shapes.removeElementAt(0); // the circle has been removed

        for (int i = 0; i < _shapes.size(); i++) {
            assertTrue(_shapes.get(i).getShape() instanceof Triangle_2D);
        }



    }

    @Test
    void testAddAt() {
        ////Circle///  ---> index 0
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle/// index 1
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);


        //Adding triangle at index 0 instead the circle
        Point_2D[] trianglePoints2 = {new Point_2D(6,6),new Point_2D(2,2),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints2);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.addAt(_gs, 0);

        // check by order according the index
        assertTrue(_shapes.get(0).getShape() instanceof Triangle_2D);

        assertTrue(_shapes.get(1).getShape() instanceof Circle_2D);

        assertTrue(_shapes.get(2).getShape() instanceof Triangle_2D);


    }

    @Test
    void testAdd() {
        ////Circle///  ---> index 0
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle/// index 1
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

        //	the size should be 2

        assertTrue(_shapes.size() == 2);

    }

    @Test
    void testCopy() {
        ////Circle///
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle///
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

        /////////////////////////////////////////////////////////////
        GUI_Shape_Collection copyShapes =  _shapes.copy();

        for (int i = 0; i < copyShapes.size(); i++) {
            GUI_Shape guiShape = copyShapes.get(i);
            GeoShape copyshape = guiShape.getShape();

            //// comaparing the GUIShape //////
            assertEquals(guiShape.isFilled(), _shapes.get(i).isFilled());
            assertEquals(guiShape.getColor(), _shapes.get(i).getColor());
            assertEquals(guiShape.toString(), _shapes.get(i).toString());


            //// comaparing the GeoShape //////
            assertEquals(copyshape.area(), _shapes.get(i).getShape().area());
            assertEquals(copyshape.perimeter(), _shapes.get(i).getShape().perimeter());
            assertEquals(copyshape.toString(), _shapes.get(i).getShape().toString());
            assertEquals(copyshape.getPoints(), _shapes.get(i).getShape().getPoints());
        }



    }

    @Test
    void testSort() {
        ////Circle///    --- > area is smaller then the triangle
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle/// --- > area is greater then the circle
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);


        coperator = new ShapeComp(Ex4_Const.Sort_By_Area);
        _shapes.sort(coperator);

        // check print triangle first should be index 0
        for (int i = 0; i < _shapes.size(); i++) {
            System.out.println(_shapes.get(i).getShape().getClass().getSimpleName());
        }
        assertTrue(_shapes.get(0).getShape() instanceof Triangle_2D);


        coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
        _shapes.sort(coperator);


        // check print circle first
        for (int i = 0; i < _shapes.size(); i++) {
            System.out.println(_shapes.get(i).getShape().getClass().getSimpleName());
        }

        assertTrue(_shapes.get(0).getShape() instanceof Circle_2D);

    }

    @Test
    void testRemoveAll() {
        _shapes.removeAll();

        //the size should be 0
        assertTrue(_shapes.size() == 0);
    }

    @Test
    void testSave() {

        ////Circle///
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle///
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

//			 System.out.println(_shapes.size());

        _shapes.save(path);

        File file = new File(path);
        try {
            String st;
            BufferedReader br = new BufferedReader(new FileReader(file));

            for (int i = 0; i < _shapes.size(); i++) {
                if((st = br.readLine()) != null) {
                    _gs = _shapes.get(i);

                    int Color = _gs.getColor().getRGB();
                    boolean isFilled = _gs.isFilled();
                    String ShapeclassName = _gs.getShape().getClass().getSimpleName();
                    String shapePoints = _gs.getShape().toString();
                    int Tag =  _gs.getTag();

                    assertEquals(st ,"GUIShape," + Color +","+isFilled+","+Tag+","+ShapeclassName+","+shapePoints);
                }


            }

            _shapes.removeAll();

//				  _shapes.removeAll(); // clear the shapes
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO: handle exception


    }

    /**
     *
     * |
     * |
     * |
     * |
     * |
     * v
     * */     /**@Test_should_work_at_the_second_time_press!!! */
    /** Logic: the first time it creates the file in the src and in the second time gets loaded.... */
    @Test
    void testLoad() {
        // Checking if the File exist


        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            assertTrue(true);
        }else {
            fail("file Not Found");
        }

        _shapes.load(path);
        assertTrue(_shapes.size()> 0); // according to the save method the numbers of shapes should be the same;



//	   System.out.println(_shapes.size()+ " Load");



    }

    @Test
    void testToString() {

        ////Circle///
        Point_2D cen = new Point_2D(5,5);
        double rad = 3;
        gs = new Circle_2D(cen, rad);
        _gs = new GUIShape(gs,true, Color.blue,0);
        _shapes.add(_gs);

        ////Triangle///
        Point_2D[] trianglePoints = {new Point_2D(5,5),new Point_2D(1,1),new Point_2D(-5,5)};
        gs = new Triangle_2D(trianglePoints);
        _gs = new GUIShape(gs,false, Color.red,0);
        _shapes.add(_gs);

        String ans = "";

        for (int i = 0; i < _shapes.size(); i++) {
            ans += _shapes.get(i).toString();
        }

        assertEquals(_shapes.toString(),ans);


    }

}

package ex4.gui;

import org.junit.jupiter.api.Test;

import java.awt.*;
import ex4.geo.GeoShape;
import ex4.geo.Point_2D;
import ex4.geo.Rect_2D;


import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {
    private boolean _fill = false;
    private Color _color;
    private Point_2D point = new Point_2D(2,2);
    Point_2D[] rectPoints = {point,point,point,point};
    private Rect_2D rect = new Rect_2D(rectPoints);

    private GeoShape gs = null;

    GUIShape gg = new GUIShape(gs,_fill, _color, 0);


    @Test
    void setShape() {
        gg.setShape(rect);

        assertEquals(this.gg.getShape(), rect);

    }
}
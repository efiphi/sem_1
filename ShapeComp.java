package ex4.geo;

import java.util.Comparator;

import ex4.ex4.Ex4_Const;
import ex4.gui.GUI_Shape;

/**
 * This class represents a Comparator over GUI_Shapes -
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shape>{
    //////////add your code below ///////////


    public static final Comparator<GUI_Shape> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
    private int _flag;

    public ShapeComp(int flag) {
        _flag = flag;
    }


    //if o1 is bigger 1 if o2 is bigger -1 else 0
    @Override
    public int compare(GUI_Shape o1, GUI_Shape o2) {
        int ans=0;
        if(_flag == Ex4_Const.Sort_By_toString) {
            ans = o1.toString().compareTo(o2.toString());
        }
        if(_flag == Ex4_Const.Sort_By_Anti_toString) {
            ans = -1*(o1.toString().compareTo(o2.toString()));
        }
        if(_flag == Ex4_Const.Sort_By_Area) {
            double d1 = o1.getShape().area();
            double d2 = o2.getShape().area();
            if(d1<d2) {ans  = -1;}
            if(d2<d1) {ans = 1;}
        }
        if(_flag == Ex4_Const.Sort_By_Anti_Area) {
            double d1 = o1.getShape().area();
            double d2 = o2.getShape().area();
            if(d1<d2) {ans  = 1;}
            if(d2<d1) {ans = -1;}
        }
        if(_flag == Ex4_Const.Sort_By_Perimeter) {
            double d1 = o1.getShape().perimeter();
            double d2 = o2.getShape().perimeter();
            if(d1<d2) {ans  = -1;}
            if(d2<d1) {ans = 1;}
        }
        if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
            double d1 = o1.getShape().perimeter();
            double d2 = o2.getShape().perimeter();
            if(d1<d2) {ans  = 1;}
            if(d2<d1) {ans = -1;}
        }
        if(_flag == Ex4_Const.Sort_By_Tag) {
            double d1 = o1.getTag();
            double d2 = o2.getTag();
            if(d1<d2) {ans  = -1;}
            if(d2<d1) {ans = 1;}
        }
        if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
            double d1 = o1.getTag();
            double d2 = o2.getTag();
            if(d1<d2) {ans  = 1;}
            if(d2<d1) {ans = -1;}
        }
        return ans;
    }


}
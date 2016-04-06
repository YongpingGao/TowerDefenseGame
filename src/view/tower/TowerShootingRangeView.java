package view.tower;

import view.map.Position;
import view.map.Drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by yongpinggao on 3/19/16.
 */
public class TowerShootingRangeView {

    private Ellipse2D towerRangeCircle;
    private int range;

    public TowerShootingRangeView(Position position, int range){
        this.range = range;
        towerRangeCircle = new Ellipse2D.Float(position.getX() + Drawing.CELL_SIZE / 2 - range / 2, position.getY() + Drawing.CELL_SIZE  / 2 - range / 2, range, range);
    }


    public Ellipse2D getTowerRangeCircle() {
        return towerRangeCircle;
    }

    public Rectangle getBounds(){
        return towerRangeCircle.getBounds();
    }

    public int getRange() {
        return range;
    }


}

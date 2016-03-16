package model.tower;

import model.drawing.Drawing;

import java.awt.*;

/**
 * Created by yongpinggao on 3/15/16.
 */
public interface DrawingShootingEffect {
    int CELL_SIZE = Drawing.CELL_SIZE;
    void drawShootingEffect(Graphics g);
}

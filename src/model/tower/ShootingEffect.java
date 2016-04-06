package model.tower;

import java.awt.*;

/**
 * Created by yongpinggao on 3/14/16.
 */
public class ShootingEffect {

    private Color color;
    private int width;

    public ShootingEffect(Color color, int width) {
        this.color = color;
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

}

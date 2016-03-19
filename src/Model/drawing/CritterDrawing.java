package model.drawing;

import model.critter.Critter;
import model.critter.CritterCollection;
import model.imagecollection.CritterImageCollection;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class CritterDrawing extends Drawing{

    public static void drawCritters(Graphics g, ImageObserver observer){
        Graphics2D g2d = (Graphics2D) g.create();
        for(Critter c : CritterCollection.critters){
            if(c.isVisible()) {
                g2d.drawImage(CritterImageCollection.critterImages.get(c.getCritterName()), c.getCurrentPosX(), c.getCurrentPosY(),observer);
            }
        }
        g2d.dispose();
    }

    public static  void drawHealthBar(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        for(Critter c : CritterCollection.critters){
            if(c.isVisible()) {
                if(c.getHealthBarLength() > 0.6){
                    g2d.setColor(Color.GREEN);
                } else if (c.getHealthBarLength() > 0.3 && c.getHealthBarLength() < 0.6) {
                    g2d.setColor(Color.YELLOW);
                } else g2d.setColor(Color.RED);
                g2d.fillRect(c.getCurrentPosX(), c.getCurrentPosY() - 5, (int)(c.getHealthBarLength() * CELL_SIZE), 3);
            }
        }
        g2d.dispose();
    }

}

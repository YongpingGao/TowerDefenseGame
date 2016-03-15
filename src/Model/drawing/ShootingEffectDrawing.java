package model.drawing;

import model.map.GameMap;
import model.tower.Tower;
import model.tower.TowerCollection;


import java.awt.*;

/**
 * Created by yongpinggao on 3/14/16.
 */
public class ShootingEffectDrawing extends Drawing {

    public static void drawShootingEffect(Graphics g, TowerCollection towerCollection) {
        Graphics2D g2d = (Graphics2D) g.create();
        for(Tower tower: towerCollection.getTowers().values()){
            g2d.setStroke(tower.getShootingEffect());
                if(tower.getCritterUnderAttack() != null && tower.isShooting()){
                    g2d.drawLine(tower.getPositionX()+ CELL_SIZE / 2, tower.getPositionY()+ CELL_SIZE / 2,
                            tower.getCritterUnderAttack().getCurrentPosX() + CELL_SIZE / 2, tower.getCritterUnderAttack().getCurrentPosY() + CELL_SIZE / 2);
                }
        }
        g2d.dispose();
    }

}

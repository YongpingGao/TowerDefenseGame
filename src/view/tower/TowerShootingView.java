package view.tower;
import view.map.Drawing;
import view.map.Position;
import model.tower.ShootingEffect;
import protocol.TowerDidShotDelegate;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Set;

/**
 * Created by yongpinggao on 3/19/16.
 */
public class TowerShootingView implements TowerDidShotDelegate {

    private Position towerPosition;

    private ShootingEffect shootingEffect;

    private Position critterPosition;

    private int range;

    public TowerShootingView(Position towerPosition, ShootingEffect shootingEffect) {
        this.towerPosition = towerPosition;
        this.shootingEffect = shootingEffect;
    }

    public TowerShootingView(Position towerPosition, int range, ShootingEffect shootingEffect) {
        this.towerPosition = towerPosition;
        this.shootingEffect = shootingEffect;
        this.range = range;
    }

    public void drawShootingEffect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(shootingEffect.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(shootingEffect.getColor());

        if(range == 0 && critterPosition != null) { // one on one
            g2d.drawLine(towerPosition.getCenterPosition().getX(), towerPosition.getCenterPosition().getY(), critterPosition.getCenterPosition().getX(), critterPosition.getCenterPosition().getY());
        } else if (range != 0) { // area of effect
            Ellipse2D towerRangeCircle = new Ellipse2D.Float(towerPosition.getX() + Drawing.CELL_SIZE / 2 - range / 2, towerPosition.getY() + Drawing.CELL_SIZE  / 2 - range / 2, range, range);
            g2d.draw(towerRangeCircle);
        }

        g2d.dispose();
    }

    @Override
    public void towerDidShotAt(Position critterPosition) {
        this.critterPosition = critterPosition;
    }
}

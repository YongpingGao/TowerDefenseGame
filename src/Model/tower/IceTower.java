package model.tower;

import model.critter.Critter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongpinggao on 3/15/16.
 */
// Freezing the critter for a time
public class IceTower extends Tower implements ShootingBehavior, DrawingShootingEffect{

    boolean isShooting;
    protected Timer shootTimer;
    protected Set<Critter> crittersInRange;

    public IceTower(int level){
        if(level <= MAX_LEVEL) {
            crittersInRange = new HashSet<>();
            highResolutionTowerImageName = TowerName.TowerBH;
            this.level = level;
            shootingEffect = ShootingEffect.getStoke(ShootingEffect.BlueWeek);
            initTower();

            shootTimer = new Timer(500 - rateOfFire, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isShooting = !isShooting;
                    if(!crittersInRange.isEmpty()){
                        shoot();
                    }
                    else critterUnderAttack = null;
                }
            });
            shootTimer.start();
        }
    }
    private void initTower(){
        specification = "<html>" + "Ice Tower" + "<br> Level: " + level + "<br> Good at attack fast creatures with its freezing effect</html>";
        switch(level){
            case 1:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerName = TowerName.TowerB1;
                range = 80;
                rateOfFire = 200;
                power = 20;
                break;
            case 2:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerName = TowerName.TowerB2;
                range = 90;
                rateOfFire = 300;
                power = 30;
                break;
            case 3:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerName = TowerName.TowerB3;
                range = 100;
                rateOfFire = 400;
                power = 40;
                break;
            default:
                towerName = TowerName.TowerNull;
        }
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
        initTower();
        setPosition(new int[]{positionX, positionY});
    }


    @Override
    public void shoot() {
        super.shoot();
        critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange);
        if(isShooting) { //if critter is get attacked(a line is drawn)
            int health = critterUnderAttack.getCurrentHealth();
            health -= power;
            if(health > 0)
                critterUnderAttack.setCurrentHealth(health);
            else {
                crittersInRange.remove(critterUnderAttack);
                critterUnderAttack = null;
            }
        }
    }

    @Override
    public Set<Critter> getCrittersInRange() {
        return crittersInRange;
    }

    @Override
    public void drawShootingEffect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(this.getShootingEffect());
        if(critterUnderAttack != null && isShooting){
            g2d.drawLine(positionX + CELL_SIZE / 2, positionY + CELL_SIZE / 2,
                    critterUnderAttack.getCurrentPosX() + CELL_SIZE / 2, critterUnderAttack.getCurrentPosY() + CELL_SIZE / 2);
        }
        g2d.dispose();
    }
}

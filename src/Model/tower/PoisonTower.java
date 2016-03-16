package model.tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * Created by yongpinggao on 3/15/16.
 */
public class PoisonTower extends Tower implements ShootingBehavior, DrawingShootingEffect {

    protected Timer shootTimer;
    int poisonTime;
    int slowDownMoveSpeed;
    int continuesDamage;
    int damageTimeInterval;


    public PoisonTower(int level){
        if(level <= MAX_LEVEL) {
            crittersInRange = new HashSet<>();
            highResolutionTowerImageName = TowerName.TowerCH;
            this.level = level;
            shootingEffect = ShootingEffect.getStoke(ShootingEffect.PoisonEffect);
            initTower();
            shootTimer = new Timer(1000 - rateOfFire, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    powerOn = true;
                    shoot();
                }
            });
            shootTimer.start();
        }
    }
    private void initTower(){
        specification = "<html>" + "Poison Tower" + "<br> Level: " + level + "<br> Good at attack huge creatures with its poison effect</html>";
        switch(level){
            case 1:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerName = TowerName.TowerC1;
                range = 80;
                rateOfFire = 10;
                poisonTime = 2000;
                slowDownMoveSpeed = 2;
                continuesDamage = 2;
                damageTimeInterval = 300;
                break;
            case 2:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerName = TowerName.TowerC2;
                range = 90;
                rateOfFire = 20;
                poisonTime = 3000;
                slowDownMoveSpeed = 3;
                continuesDamage = 4;
                damageTimeInterval = 200;
                break;
            case 3:
                buyPrice = 60.0;
                sellPrice = 30.0;
                towerName = TowerName.TowerC3;
                range = 100;
                rateOfFire = 30;
                poisonTime = 4000;
                slowDownMoveSpeed = 4;
                continuesDamage = 8;
                damageTimeInterval = 100;
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
        if(powerOn && critterUnderAttack != null && critterUnderAttack.getCurrentMoveSpeed() == critterUnderAttack.getInitialMoveSpeed()) { //if critter is attacked(a line is drawn)
            critterUnderAttack.setCurrentMoveSpeed(critterUnderAttack.getCurrentMoveSpeed() - slowDownMoveSpeed);

            Timer poisonTimer = new Timer(poisonTime, critterUnderAttack);
            poisonTimer.setInitialDelay(poisonTime);
            poisonTimer.setRepeats(false);
            critterUnderAttack.setMovingTimer(poisonTimer);
            critterUnderAttack.getMovingTimer().start();

            Timer continuesDamageTimer = new Timer(damageTimeInterval, critterUnderAttack);
            critterUnderAttack.setContinuesDamage(continuesDamage);
            continuesDamageTimer.setActionCommand("POISON");
            critterUnderAttack.setInnerTimer(continuesDamageTimer);
            critterUnderAttack.getInnerTimer().start();

            if(critterUnderAttack.getCurrentHealth() <= 0) {
                crittersInRange.remove(critterUnderAttack);
                critterUnderAttack = null;
            }
        } else critterUnderAttack = null;
    }

    @Override
    public void drawShootingEffect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(this.getShootingEffect());
        if(critterUnderAttack != null && powerOn){
            g2d.setColor(Color.RED);
            g2d.drawLine(positionX + CELL_SIZE / 2, positionY + CELL_SIZE / 2,
                    critterUnderAttack.getCurrentPosX() + CELL_SIZE / 2, critterUnderAttack.getCurrentPosY() + CELL_SIZE / 2);
        }
        powerOn = false;
        g2d.dispose();
    }

}

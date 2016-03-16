package model.tower;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;


/**
 * Created by yongpinggao on 3/15/16.
 */
// Freezing the critter for a time (have a higher priority to poison tower)
public class IceTower extends Tower implements ShootingBehavior, DrawingShootingEffect{


    protected Timer shootTimer;
    int frozenTime;


    public IceTower(int level){
        if(level <= MAX_LEVEL) {
            crittersInRange = new HashSet<>();
            highResolutionTowerImageName = TowerName.TowerBH;
            this.level = level;
            shootingEffect = ShootingEffect.getStoke(ShootingEffect.IceEffect);
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
        specification = "<html>" + "Ice Tower" + "<br> Level: " + level + "<br> Good at attack fast creatures with its freezing effect</html>";
        switch(level){
            case 1:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerName = TowerName.TowerB1;
                range = 80;
                rateOfFire = 10;
                power = 0;
                frozenTime = 1000;
                break;
            case 2:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerName = TowerName.TowerB2;
                range = 90;
                rateOfFire = 20;
                frozenTime = 1500;
                power = 0;
                break;
            case 3:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerName = TowerName.TowerB3;
                range = 100;
                rateOfFire = 30;
                frozenTime = 2000;
                power = 0;
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
        if(powerOn && critterUnderAttack != null && critterUnderAttack.getCurrentMoveSpeed() != 0) { //if critter is attacked(a line is drawn)
            critterUnderAttack.setCurrentMoveSpeed(0);

            Timer freezeTimer = new Timer(frozenTime, critterUnderAttack);
            freezeTimer.setInitialDelay(frozenTime);
            freezeTimer.setRepeats(false);
            critterUnderAttack.setMovingTimer(freezeTimer);
            critterUnderAttack.getMovingTimer().start();

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
            g2d.setColor(Color.WHITE);
            g2d.drawLine(positionX + CELL_SIZE / 2, positionY + CELL_SIZE / 2,
                    critterUnderAttack.getCurrentPosX() + CELL_SIZE / 2, critterUnderAttack.getCurrentPosY() + CELL_SIZE / 2);
        }
        powerOn = false;
        g2d.dispose();
    }
}

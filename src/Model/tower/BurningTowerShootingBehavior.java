package model.tower;

import model.critter.Critter;
import model.tower.shootingstrategy.TowerShootingStrategy;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * Created by yongpinggao on 3/24/16.
 */
public class BurningTowerShootingBehavior extends TowerShootingBehavior {
    private int burningDamage;

    public BurningTowerShootingBehavior(int power, int rateOfFire, int burningDamage, TowerShootingStrategy shootingStrategy) {
        this.power = power;
        this.rateOfFire = rateOfFire;
        this.shootingStrategy = shootingStrategy;
        this.burningDamage = burningDamage;
        crittersInRange = new HashSet<>();
        towerTimer = new Timer(1000 - rateOfFire, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeToShoot = true;
            }
        });
    }

    public void shoot() {
        Critter critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange);
        if(critterUnderAttack != null && !critterUnderAttack.isKilled()) {
            towerDidShotDelegate.towerDidShotAt(critterUnderAttack.getMovingBehavior().getCurrentPosition());
            int health = critterUnderAttack.getCurrentHealth();
            health -= power;
            critterUnderAttack.setCurrentHealth(health);

            Timer burningTimer = new Timer(0, critterUnderAttack);
            burningTimer.setInitialDelay(1000);
            burningTimer.setRepeats(false);
            burningTimer.setActionCommand("BURNING_TOWER");
            critterUnderAttack.setSpecicalEffectTimer(burningTimer);
            critterUnderAttack.getSpecicalEffectTimer().start();

            Timer losingHealthTimer = new Timer(200, critterUnderAttack);
            critterUnderAttack.setDamage(burningDamage);
            critterUnderAttack.setInnerTimer(losingHealthTimer);
            critterUnderAttack.getInnerTimer().start();

        } else  towerDidShotDelegate.towerDidShotAt(null);
        if (critterUnderAttack.getCurrentHealth() <= 0) {
            crittersInRange.remove(critterUnderAttack);
        }
    }
}

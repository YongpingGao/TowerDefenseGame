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
public class IceTowerShootingBehavior extends TowerShootingBehavior {

    private int frozenTime;

    public IceTowerShootingBehavior(int frozenTime, int rateOfFire, TowerShootingStrategy shootingStrategy) {
        this.frozenTime = frozenTime;
        this.rateOfFire = rateOfFire;
        this.shootingStrategy = shootingStrategy;

        crittersInRange = new HashSet<>();
        towerTimer = new Timer(1000 - rateOfFire, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeToShoot = true;
            }
        });
    }

    public void shoot() {
        super.shoot();
        Critter critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange);
        if(critterUnderAttack != null && !critterUnderAttack.getSpecicalEffectTimer().isRunning() && !critterUnderAttack.isKilled()) {
            towerDidShotDelegate.towerDidShotAt(critterUnderAttack.getMovingBehavior().getCurrentPosition());
            critterUnderAttack.getMovingBehavior().getMovingTimer().stop();
            Timer frozenTimer = new Timer(0, critterUnderAttack);
            frozenTimer.setInitialDelay(frozenTime);
            frozenTimer.setRepeats(false);
            frozenTimer.setActionCommand("ICE_TOWER");
            critterUnderAttack.setSpecicalEffectTimer(frozenTimer);
            critterUnderAttack.getSpecicalEffectTimer().start();
        } else towerDidShotDelegate.towerDidShotAt(null);

        if(critterUnderAttack.getCurrentHealth() <= 0) {
            crittersInRange.remove(critterUnderAttack);
        }
    }
}

package model.tower;

import model.critter.Critter;
import model.tower.shootingstrategy.TowerShootingStrategy;
import protocol.TowerDidShotDelegate;

import javax.swing.*;
import java.util.Set;

/**
 * Created by yongpinggao on 3/19/16.
 */
public class TowerShootingBehavior {
    // tower shooting behavior
    protected int power;
    protected int rateOfFire;
    protected TowerShootingStrategy shootingStrategy;
    protected boolean isShooting;
    protected boolean timeToShoot;

    protected Timer towerTimer;

    protected Set<Critter> crittersInRange;

    protected TowerDidShotDelegate towerDidShotDelegate;

    public int getPower() {
        return power;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public Set<Critter> getCrittersInRange() {
        return crittersInRange;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        if(shooting) {
            towerTimer.start();
        } else {
            towerTimer.stop();
        }
        isShooting = shooting;
    }

    public boolean isTimeToShoot() {
        return timeToShoot;
    }

    public void setTimeToShoot(boolean timeToShoot) {
        this.timeToShoot = timeToShoot;
    }

    public void setTowerDidShotDelegate(TowerDidShotDelegate towerDidShotDelegate) {
        this.towerDidShotDelegate = towerDidShotDelegate;
    }



    public void shoot() {}



}

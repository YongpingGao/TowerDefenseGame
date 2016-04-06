package model.tower;

import model.critter.Critter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;


/**
 * Created by yongpinggao on 3/24/16.
 */
public class SplashTowerShootingBehavior extends TowerShootingBehavior {


    public SplashTowerShootingBehavior(int power, int rateOfFire) {
        isShooting = true;
        this.power = power;
        this.rateOfFire = rateOfFire;
        crittersInRange = new HashSet<>();
        towerTimer = new Timer(1000 - rateOfFire, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeToShoot = true;
            }
        });
    }

    public void shoot() {
        // to avoid ConcurrentModificationException for removing item during iteration
        HashSet<Critter> killedCritters = new HashSet<>();

        if(!crittersInRange.isEmpty()) {
            for(Critter c: crittersInRange){
                int health = c.getCurrentHealth();
                health -= power;
                c.setCurrentHealth(health);
                if(c.getCurrentHealth() <= 0) {
                    killedCritters.add(c);
                }
            }
            crittersInRange.removeAll(killedCritters);
        }
    }
}

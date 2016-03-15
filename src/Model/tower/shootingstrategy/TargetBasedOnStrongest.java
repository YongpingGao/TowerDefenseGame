package model.tower.shootingstrategy;

import model.critter.Critter;

import java.util.Set;

/**
 * Created by yongpinggao on 3/14/16.
 */
public class TargetBasedOnStrongest implements TowerShootingStrategy {
    @Override
    public Critter targetOnCritters(Set<Critter> var1) {
        System.out.println("Change to strongest!!!");
        return new Critter();
    }
}

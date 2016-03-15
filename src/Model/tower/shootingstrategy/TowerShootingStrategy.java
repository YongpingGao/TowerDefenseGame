package model.tower.shootingstrategy;

import model.critter.Critter;

import java.util.Set;

/**
 * Created by yongpinggao on 3/14/16.
 */
public interface TowerShootingStrategy {

     Critter targetOnCritters(Set<Critter> crittersInRange);
}

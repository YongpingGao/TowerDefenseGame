package model.tower.shootingstrategy;

import model.critter.Critter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yongpinggao on 3/14/16.
 */
public class TargetBasedOnStrongest implements TowerShootingStrategy {
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange) {
        if(crittersInRange.size() > 0){
            Iterator<Critter> iterator = crittersInRange.iterator();
            Critter strongestCritter = iterator.next();
            while(iterator.hasNext()){
                Critter c = iterator.next();
                if(c.getCurrentHealth() > strongestCritter.getCurrentHealth()){
                    strongestCritter = c;
                }
            }
            return strongestCritter;
        } else return null;
    }
}

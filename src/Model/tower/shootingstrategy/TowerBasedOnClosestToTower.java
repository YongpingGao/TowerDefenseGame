package model.tower.shootingstrategy;

import model.critter.Critter;

import java.util.Set;

/**
 * Created by yongpinggao on 3/25/16.
 */
public class TowerBasedOnClosestToTower implements TowerShootingStrategy {
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange) {
//        if(!crittersInRange.isEmpty()){
//            Iterator<Critter> iterator = crittersInRange.iterator();
//            Critter closestCritter = iterator.next();
//            while(iterator.hasNext()){
//                Critter c = iterator.next();
//                if(c.getMovingBehavior().getCurrentPosition().distanceTo(tower.getPosition().getCenterPosition()) <
//                        closestCritter.getMovingBehavior().getCurrentPosition().distanceTo(tower.getPosition().getCenterPosition())) {
//                    closestCritter = c;
//                }
//            }
//            return closestCritter;
//        } else return null;
        return null;
    }
}

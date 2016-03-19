package model.critter;

import model.map.GameMap;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class CritterCollection {
    // Java Collection classes are fail-fast which means that if the Collection will be changed
    // while some thread is traversing over it using iterator, the iterator.next() will throw a ConcurrentModificationException.
    // That's why we use CopyOnWriteArrayList (when game over, critters should stop moving and be cleaned)
    // JDK 1.5 minimum
    public static CopyOnWriteArrayList<Critter> critters = new CopyOnWriteArrayList<>();


    public static void addCritter(Critter critter){
        critters.add(critter);
    }

    public static void removeCritter(Critter critter) {
        critters.remove(critter);
    }

    public static void clearAllCritters(){
        currentIndex = 0;
        critters.removeAll(critters);
    }

    public static void crittersMoving(){
        for(Critter c : critters){
            c.moveThroughPathInMap();
        }
    }

    public static void setGameMapForCritters(GameMap gameMap){
        for(Critter c : critters){
            c.setGameMap(gameMap);
        }
    }

    public static int currentIndex = 0;


}

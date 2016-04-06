package model.tower;


import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerCollection {


    private HashMap<Integer, Tower> towers;

    public TowerCollection(){
        this.towers = new HashMap<>();
    }

    public HashMap<Integer, Tower> getTowers() {
        return towers;
    }

    public void addTowerAtIndex(int index, Tower tower){
        LoggerCollection.getInstance().addLog(new Log(LogType.Tower, index, "Player plant a new tower: " + tower.towerType + " at position " + index));
        towers.put(index, tower);
    }

    public void removeTowerAtIndex(int index){
        LoggerCollection.getInstance().addLog(new Log(LogType.Tower, index, "Player sell a tower: " + towers.get(index).towerType + " at position " + index));
        towers.remove(index);
    }

}

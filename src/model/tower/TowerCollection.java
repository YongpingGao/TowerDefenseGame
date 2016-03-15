package model.tower;


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
        towers.put(index, tower);
    }

    public void removeTowerAtIndex(int index){
        towers.remove(index);
    }


    public void drawShootingEffect(Graphics g){
        for (Tower t: towers.values()){
            t.drawShootingEffect(g);
        }
    }
}

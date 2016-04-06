package model.tower;


import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;
import view.map.Position;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;


/**
 * Created by yongpinggao on 3/13/16.
 */
public class Tower {

    public final static int MAX_LEVEL = 3;

    // tower normal attributes
    protected int level;
    protected TowerType towerType;
    protected String specification;
    protected double buyPrice;
    protected double sellPrice;
    protected int range;

    protected String hdImageName;

    protected TowerShootingBehavior towerShootingBehavior;

    // tower shooting range attributes
    protected Position position;

    // View
    protected TowerView towerView;
    protected TowerShootingView towerShootingView;
    protected TowerShootingRangeView towerShootingRangeView;


    public void setLevel(int level) {
        this.level = level;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public TowerView getTowerView() {
        return towerView;
    }

    public TowerShootingRangeView getTowerShootingRangeView() {
        return towerShootingRangeView;
    }

    public String getSpecification() {
        return specification;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public TowerShootingBehavior getTowerShootingBehavior() {
        return towerShootingBehavior;
    }

    public String getHdImageName() {
        return hdImageName;
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return range;
    }

    public TowerShootingView getTowerShootingView() {
        return towerShootingView;
    }

    public TowerType getTowerType() {
        return towerType;
    }
}

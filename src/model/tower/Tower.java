package model.tower;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class Tower {

    public final static int MAX_LEVEL = 3;

    int level;
    TowerName towerName = TowerName.TowerNull;
    TowerName highReslutionTowerImageName;

    // tower normal attributes
    double buyPrice;
    double sellPrice;
    String specification;

    // tower shooting attributes
    int range;
    int power;
    int rateOfFire;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TowerName getTowerName() {
        return towerName;
    }

    public void setTowerName(TowerName towerName) {
        this.towerName = towerName;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

    public TowerName getHighReslutionTowerImageName() {
        return highReslutionTowerImageName;
    }

    public void setHighReslutionTowerImageName(TowerName highReslutionTowerImageName) {
        this.highReslutionTowerImageName = highReslutionTowerImageName;
    }
}

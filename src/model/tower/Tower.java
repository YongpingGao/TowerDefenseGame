package model.tower;

import model.critter.Critter;
import model.drawing.Drawing;
import model.tower.shootingstrategy.*;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class Tower implements ShootingBehavior, DrawingShootingEffect{

    public final static int MAX_LEVEL = 3;

    int level;
    TowerName towerName;
    TowerName highResolutionTowerImageName;

    // tower normal attributes
    double buyPrice;
    double sellPrice;
    String specification;
    boolean powerOn;

    int positionX;
    int positionY;

    // tower shooting attributes
    int range;
    int power;
    int rateOfFire;

    protected Set<Critter> crittersInRange;
    protected Critter critterUnderAttack;
    protected Ellipse2D towerRangeCircle;
    protected BasicStroke shootingEffect;
    protected TowerShootingStrategy shootingStrategy;

    public Tower() {
        crittersInRange = new HashSet<>();
        shootingEffect = new BasicStroke();
//        shootingStrategy = new TargetBasedOnWeakest();
    }

    public Rectangle getBound(){
        return towerRangeCircle.getBounds();
    }

    public void setPosition(int[] pos){
        positionX = pos[0];
        positionY = pos[1];
        towerRangeCircle = new Ellipse2D.Float(positionX + Drawing.CELL_SIZE / 2 - range / 2, positionY + Drawing.CELL_SIZE  / 2 - range / 2, range, range);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Ellipse2D getTowerRangeCircle() {
        return towerRangeCircle;
    }


    public void setTowerRangeCircle(Ellipse2D towerRangeCircle) {
        this.towerRangeCircle = towerRangeCircle;
    }

    public BasicStroke getShootingEffect() {
        return shootingEffect;
    }

    public void setShootingEffect(BasicStroke shootingEffect) {
        this.shootingEffect = shootingEffect;
    }


    public TowerShootingStrategy getShootingStrategy() {
        return shootingStrategy;
    }

    public void setShootingStrategy(TowerShootingStrategy shootingStrategy) {
        this.shootingStrategy = shootingStrategy;
    }

    public Critter getCritterUnderAttack() {
        return critterUnderAttack;
    }

    public void setCritterUnderAttack(Critter critterUnderAttack) {
        this.critterUnderAttack = critterUnderAttack;
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

    public TowerName getHighResolutionTowerImageName() {
        return highResolutionTowerImageName;
    }

    public void setHighResolutionTowerImageName(TowerName highResolutionTowerImageName) {
        this.highResolutionTowerImageName = highResolutionTowerImageName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Critter> getCrittersInRange() {
        return crittersInRange;
    }

    @Override
    public void shoot() {}

    @Override
    public void drawShootingEffect(Graphics g) {}
}
